package com.zb.service.impl;

import com.zb.mapper.CommunityTaskMapper;
import com.zb.pojo.CommunityTask;
import com.zb.service.CommunityTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityTaskServiceImpl implements CommunityTaskService {


    @Autowired
    public CommunityTaskMapper communityTaskMapper;

    @Override
    public int updateTaskversion(CommunityTask communityTask) {
        return communityTaskMapper.updateTaskversion(communityTask);
    }

    @Override
    public int delTask(String id) {
        return communityTaskMapper.delTask(id);
    }

    @Override
    public int updateTaskTime(String id) {
        return communityTaskMapper.updateTaskTime(id);
    }

    @Override
    public List<CommunityTask> getAminuteAgoTask() {
        return communityTaskMapper.getAMinuteAgoTask();
    }

    @Override
    public CommunityTask getCommunityTaskById(String id) {
        return communityTaskMapper.getCommunityTaskById(id);
    }

    @Override
    public int updateTask(CommunityTask communityTask) {
        return communityTaskMapper.updateCommunityTask(communityTask);
    }

    @Override
    public int insertTask(CommunityTask communityTask) {
        return communityTaskMapper.insertCommunityTask(communityTask);
    }
}
