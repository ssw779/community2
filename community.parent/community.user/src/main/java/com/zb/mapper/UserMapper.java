package com.zb.mapper;

import com.zb.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 注册
     * @param user
     * @return
     */
    public int insertUser(User user);

    public User userLogin(@Param("userName") String userName, @Param("pwd") String pwd);

    public List<User> findUserList();

    public User findUserById(@Param("id") Integer id);

    public User getCurrentUser(@Param("token") String token);

    public int updatePwd(User user);

    public int deleteUser(@Param("id") Integer id);

    public String findPwdByUser(User user);
}
