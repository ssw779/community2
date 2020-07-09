package com.zb.service;

import com.zb.pojo.User;

public interface TokenService {
    /**
     * 创建token
     * @param agentStr
     * @param userCode
     * @return
     */
    public String createToken(String agentStr, String userCode);

    /**
     * 保存token
     * @param token
     *
     */
    public void saveToken(String token, User user);

}
