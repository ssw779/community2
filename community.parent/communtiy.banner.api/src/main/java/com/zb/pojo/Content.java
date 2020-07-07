package com.zb.pojo;

import java.io.Serializable;

/**
 * 广告横幅实体类
 *
 * @author 孙硕威
 */
public class Content implements Serializable {
    /**
     * 图片编号
     */
    private Integer id;
    /**
     * 图片url
     */
    private String url;
    /**
     * 图片绝对路径
     */
    private String pic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
