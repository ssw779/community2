package com.zb.controller;

import com.zb.dto.Dto;
import com.zb.dto.DtoUtil;
import com.zb.pojo.User;
import com.zb.service.TokenService;
import com.zb.service.UserService;
import com.zb.vo.UserTokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @PostMapping(value = "/insertUser")
    public Dto insertUser(User user) {
        int i = userService.insertUser(user);
        if (i>0) {
            return DtoUtil.returnSuccess("注册成功！",i);
        }
        return DtoUtil.returnSuccess("注册失败！",i);
    }

   @GetMapping(value = "/userLogin")
    public Dto userLogin(String userName, String pwd, HttpServletRequest request) {
       System.out.println("进入登录！");
       //登录
       User userLogin = userService.userLogin(userName, pwd);
       //验证登录
       if (userLogin != null) {
           //不为空 调用创建token的方法 获取请求头和当前登录对象的Username
           String token = tokenService.createToken(request.getHeader("user-agent"), userLogin.getUserName());
           //调用存储token的方法 将token和登录对象存储
           tokenService.saveToken(token, userLogin);
           //创建一个UserTokenVo工具对象
           UserTokenVo userTokenVo = new UserTokenVo();
           userTokenVo.setToken(token);
           userTokenVo.setExpTime(System.currentTimeMillis());
           userTokenVo.setGenTime(System.currentTimeMillis() * 2 * 60 * 60);
           return DtoUtil.returnSuccess("登录成功！",userTokenVo);
       }else{
           return DtoUtil.returnFail("登录失败！","1001");
       }
   }
    @GetMapping(value = "/findUserList")
    public Dto findUserList() {
        List<User> userList = userService.findUserList();
        return DtoUtil.returnSuccess("ok",userList);
    }

    @GetMapping(value = "/findUserById")
    public Dto findUserById(Integer id) {
        User user = userService.findUserById(id);
        return DtoUtil.returnSuccess("ok",user);
    }
    @GetMapping(value = "/getCurrentUser")
    public User getCurrentUser(String token) {

        return userService.getCurrentUser(token);
    }
    @PostMapping(value = "/updatePwd")
    public Dto updatePwd(User user) {
        int i = userService.updatePwd(user);
        if (i>0) {
            return DtoUtil.returnSuccess("修改成功！",i);
        }
        return DtoUtil.returnSuccess("修改失败！",i);
    }

    @GetMapping("/deleteUser")
    public int deleteUser(Integer id) {
        return userService.deleteUser(id);
    }
}
