package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.mapper.UserMapper;
import com.zb.pojo.User;
import com.zb.service.UserService;
import com.zb.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private RedisUtil redisUtil;



    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User userLogin(String userName,String pwd) {
        return userMapper.userLogin(userName, pwd);
    }

    @Override
    public List<User> findUserList() {
        String key = "userList";
        List<User> list = null;
        if (redisTemplate.hasKey(key)) {
            System.out.println("查询redis");
            String jsonStr = redisTemplate.opsForValue().get(key).toString();
            list = JSON.parseArray(jsonStr, User.class);
        } else {
            System.out.println("查询数据库");
            list = userMapper.findUserList();
            String jsonString = JSON.toJSONString(list);
            redisTemplate.opsForValue().set(key, jsonString, 60, TimeUnit.SECONDS);
            System.out.println("查询数据库完毕");
        }
        return list;
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User getCurrentUser(String token) {
        String json = redisUtil.get(token).toString();
        if (json==null){
            return null;
        }
        User user = JSON.parseObject(json, User.class);
        return user;
    }

    @Override
    public int updatePwd(User user) {
        return userMapper.updatePwd(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public String findPwdByUser(User user) {
        return userMapper.findPwdByUser(user);
    }
}
