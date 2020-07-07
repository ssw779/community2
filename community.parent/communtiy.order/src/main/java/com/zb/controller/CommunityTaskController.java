package com.zb.controller;

import com.zb.pojo.CommunityTask;
import com.zb.service.CommunityTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/task")
public class CommunityTaskController {
    @Autowired
    private CommunityTaskService communityTaskService;

    @PostMapping(value = "updateTask")
    public int updateTask(CommunityTask communityTask) {
        return communityTaskService.updateTask(communityTask);
    }

    @PostMapping(value = "/insertTask")
    public int insertTask(CommunityTask communityTask) {

        return communityTaskService.insertTask(communityTask);
    }
}
