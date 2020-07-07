package com.zb.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限过滤器
 * 不不登陆不继续执行
 * 从请求头种取出token
 * @author 孙硕威
 */
//@Component暂时不用，用的时候解开注解
public class PowerFilters extends ZuulFilter {
    @Override
    public String filterType() {//设置过滤器的类型
        //前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {//过滤器的执行顺序,数据越小过滤器先会执行
        return 0;
    }

    @Override
    public boolean shouldFilter() {//将请求是否继续传递到下一个过滤器或者是具体的微服务
        return true;
    }

    @Override
    public Object run() {//过滤器的执行方法
        //获取请求的上下文对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取request对象
        HttpServletRequest request = requestContext.getRequest();
        //获取请求中的token
        String token = request.getParameter("token");
        //验证是否登录
        if(token==null || "".equals(token)){
            //响应失败
            requestContext.setSendZuulResponse(false);
            //返回的状态信息
            requestContext.setResponseStatusCode(401);
            //返回给页面的数据
            requestContext.setResponseBody("{code:401,message:'user not login'}");
        }
        return "next";
    }
}
