package com.zb.controller;

import com.zb.pojo.UserRole;
import com.zb.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;
    @GetMapping(value = "/findUserRoleList")
    public List<UserRole> findUserRoleList(){
        return  userRoleService.findUserRoleList();
    }
}
