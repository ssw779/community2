package com.zb.feign;

import com.zb.pojo.CommunityTask;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "ordersServer")
public interface CommunityTaskFeignClient {


    @PostMapping(value = "updateTask")
    public int updateTask(CommunityTask communityTask);

    @PostMapping(value = "/insertTask")
    public int insertTask(CommunityTask communityTask);
}
