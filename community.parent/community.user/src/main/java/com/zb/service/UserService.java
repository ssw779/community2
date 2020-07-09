package com.zb.service;

import com.zb.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    public int insertUser(User user);

    public User userLogin(String userName, String pwd);

    public List<User> findUserList();

    public User findUserById(Integer id);

    public User getCurrentUser(String token);

    public int updatePwd(User user);

    public int deleteUser(Integer id);

    public String findPwdByUser(User user);

}
