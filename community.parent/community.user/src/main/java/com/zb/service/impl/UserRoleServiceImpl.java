package com.zb.service.impl;

import com.zb.mapper.UserRoleMapper;
import com.zb.pojo.UserRole;
import com.zb.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired(required = false)
    private UserRoleMapper userRoleMapper;
    @Override
    public List<UserRole> findUserRoleList() {
        return userRoleMapper.findUserRoleList();
    }
}
