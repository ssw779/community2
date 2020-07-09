package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.pojo.User;
import com.zb.service.TokenService;
import com.zb.util.MD5;
import com.zb.util.RedisUtil;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 创建token
     * @param agentStr
     * @param userCode
     * @return
     */
    @Override
    public String createToken(String agentStr, String userCode) {
        StringBuffer token=new StringBuffer("token-");
        UserAgent userAgent = UserAgent.parseUserAgentString(agentStr);
        //判断登录状态的客户端：手机、电脑
        if (userAgent.getOperatingSystem().isMobileDevice()){
            token.append("MOBILE-");
        }else {
            token.append("PC-");
        }
        //MD5加密 长度32
        token.append(MD5.getMd5(userCode, 32)).append("-");
        //拼接一个时间戳
        token.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).append("-");
        //MD5加密 请求头 长度6位
        token.append(MD5.getMd5(agentStr, 6));
        return token.toString();//返回token字符串格式
    }

    /**
     * 保存token
     * @param token
     * @param user
     */
    @Override
    public void saveToken(String token, User user) {
        //判断token的前缀是否是token-MOBILE- 手机移动端
        if (token.startsWith("token-MOBILE-")) {
            //手机客户端存储永久登录信息
            redisUtil.set(token, JSON.toJSONString(user));
        } else {
            // PC客户端 存储2个小时登录信息
            redisUtil.set(token,  JSON.toJSONString(user),60 * 60 * 2);
        }

    }
}
