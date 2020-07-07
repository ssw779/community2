package com.zb.mapper;

import com.zb.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserRoleMapper {
    public List<UserRole> findUserRoleList();
}
