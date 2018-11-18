package org.javatribe.lottery.controller;

import com.alibaba.fastjson.JSONObject;
import org.javatribe.lottery.enums.ResultEnum;
import org.javatribe.lottery.po.User;
import org.javatribe.lottery.po.WeChatLoginResult;
import org.javatribe.lottery.util.AuthUtil;
import org.javatribe.lottery.util.CheckUtil;
import org.javatribe.lottery.util.ResultUtils;
import org.javatribe.lottery.util.TokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author 江南小俊
 * @Date 2018/11/8 11:30
 * @Version 1.0.0
 **/
@Controller
public class UserController {
    /**
     * 微信登录成功回调地址
     */
    @Value(value = "${redirect_uri}")
    private String REDIRECT_URI;
    /**
     * snsapi_userinfo、base
     */
    private String SCOPE = "snsapi_userinfo";

    /**
     * 调用微信登录
     * @return
     */
    @GetMapping("/api/login")
    public String login() {
        System.out.println("REDIRECT_URI:"+REDIRECT_URI);
        String loginUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + AuthUtil.APPID +
                "&redirect_uri=" + REDIRECT_URI +
                "&response_type=code" +
                "&scope=" + SCOPE +
                "&state=STATE#wechat_redirect";
        return "redirect:" + loginUrl;

    }

    /**
     * 微信登录成功的回调
     * @param code
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/api/callback")
    public Object callback(@RequestParam("code") String code) throws Exception {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AuthUtil.APPID +
                "&secret=" + AuthUtil.APPSERCRET +
                "&code=" + code +
                "&grant_type=authorization_code";
        JSONObject object = AuthUtil.httpGet(url);
        JSONObject userObj = null;
        WeChatLoginResult result = JSONObject.toJavaObject(object, WeChatLoginResult.class);
        if (result.getErrcode() == -1) {
            String userUrl = "https://api.weixin.qq.com/sns/userinfo?" +
                    "access_token=" + result.getAccess_token() +
                    "&openid=" + result.getOpenid() +
                    "&lang=zh_CN";
            userObj = AuthUtil.httpGet(userUrl);
            User user=JSONObject.toJavaObject(userObj,User.class);
            user.setOpenid(result.getOpenid());
            System.out.println(user.toString());
            String token=TokenUtil.createJWT(JSONObject.toJSONString(user),30*60*1000);
            return ResultUtils.success(token);
            //return "<h1 align='center'>你好，" +user.getNickname()+"<a href='md5/lottery'>我要抽奖</a></h1>"+ "<img src='"+user.getHeadimgurl()+"' width='200px' height='200px' align='center'/>";
        }
        return ResultUtils.error(ResultEnum.NOT_LOGIN);


    }

    /**
     * 响应公众号的域名配置
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @ResponseBody
    @GetMapping("/")
    public String test(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce, @RequestParam("echostr") String echostr) {
        System.out.println("test");
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }

        return "/index.html";

    }

}
