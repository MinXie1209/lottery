package org.javatribe.lottery.controller;

import org.javatribe.lottery.enums.ResultEnum;
import org.javatribe.lottery.exception.ResultException;
import org.javatribe.lottery.po.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;

/**
 * @author 江南小俊
 * @create 2018-06-17 17:32
 * @desc 空url拦截
 **/
@RestController
public class NullController {
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/**")
    public Result<String> nullUrl() throws Exception {
        String url = "";
        url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getServletPath();
        if (request.getQueryString() != null) {
            url += "?" + request.getQueryString();
        }

        System.out.println("url:"+url);
        // throw  new  NullPointerException("空指针异常");
        throw new ResultException(ResultEnum.ERROR);
    }
}
