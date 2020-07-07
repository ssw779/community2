package com.zb.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常处理类
 * 用于请求次数太多以后，进到这个类
 * 所有的错误，都会进这里
 *
 * @author 孙硕威
 */
@RestController
public class MyExcptionController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping(value = "error")
    public String error() {

        return "{'message':'too many request'}";
    }
}
