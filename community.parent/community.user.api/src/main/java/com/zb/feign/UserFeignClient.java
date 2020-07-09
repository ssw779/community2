package com.zb.feign;

import com.zb.pojo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "userserver")
public interface UserFeignClient {
    @PostMapping(value = "/insertUser")
    public int insertUser(@RequestBody User user);

    @GetMapping(value = "/userLogin")
    public User userLogin(@RequestParam("userName") String userName, @RequestParam("pwd") String pwd);

    @GetMapping(value = "/findUserList")
    public List<User> findUserList();

    @GetMapping(value = "/findUserById")
    public User findUserById(@RequestParam("id") Integer id);

    @GetMapping(value = "/getCurrentUser")
    public User getCurrentUser(@RequestParam("token") String token);

    @PostMapping(value = "/updatePwd")
    public int updatePwd(@RequestBody User user);

    @GetMapping(value = "/deleteUser")
    public int deleteUser(@RequestParam("id") Integer id);

    @PostMapping(value = "/findPwdByUser")
    public String findPwdByUser(@RequestBody User user);
}
