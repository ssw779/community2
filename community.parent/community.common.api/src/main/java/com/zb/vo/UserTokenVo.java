package com.zb.vo;


public class UserTokenVo {
    private String token;   //返回给浏览器的token唯一的标识符
    private Long genTime;   //创建时间
    private Long expTime;   //有效时间

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getGenTime() {
        return genTime;
    }

    public void setGenTime(Long genTime) {
        this.genTime = genTime;
    }

    public Long getExpTime() {
        return expTime;
    }

    public void setExpTime(Long expTime) {
        this.expTime = expTime;
    }

    public UserTokenVo() {
    }

    public UserTokenVo(String token, Long genTime, Long expTime) {
        this.token = token;
        this.genTime = genTime;
        this.expTime = expTime;
    }
}
