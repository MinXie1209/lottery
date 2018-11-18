package org.javatribe.lottery.controller;

import com.alibaba.fastjson.JSONObject;
import org.javatribe.lottery.po.Lottery;
import org.javatribe.lottery.po.Result;
import org.javatribe.lottery.po.User;
import org.javatribe.lottery.service.LotteryService;
import org.javatribe.lottery.util.LotteryUtil;
import org.javatribe.lottery.util.ResultUtils;
import org.javatribe.lottery.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class LotteryController {

    @Autowired
    LotteryService lotteryService;
    @Autowired
    private Lottery lottery;
    //初始化奖池
    private static AtomicInteger prizeNum;
    //是否还剩奖项
    private static boolean isHasPrizeNumber = true;
    private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
private static HashSet<User> users=new HashSet<>();
    /**
     * 抽奖接口
     * 1.判断token正确
     * 2.判断用户是否已经抽过奖
     * 3.判断MD5是否正确
     * 4.抽奖
     * @return
     */
    @RequestMapping("/api/{md5}/lottery")
    public Object lottery(@RequestHeader(value = "token")String token) throws Exception {
        String userJson=TokenUtil.parseJWT(token).getSubject();
        JSONObject jsonObject=JSONObject.parseObject(userJson);
        User user=JSONObject.toJavaObject(jsonObject,User.class);

        for(User user1:users){
            System.out.println(user1.toString());
        }
        if(users.contains(user)){
            return "你已经抽过奖";
        }
        else{
            initPrizeSize();
            users.add(user);
            if (isHasPrizeNumber) {
                int readPrizeNum = prizeNum.decrementAndGet();
                int myPrizeNum = readPrizeNum + 1;
                if (readPrizeNum >= 0) {
                    logger.info("抽到的号码为：" + myPrizeNum);

                    return "恭喜你，抽到了" + LotteryUtil.NumberToLevel(myPrizeNum, lottery);
                } else {
                    isHasPrizeNumber = false;
                    return "抱歉，您没中奖";
                }
            } else {
                return "抱歉，您没中奖";
            }
        }

    }

    private void initPrizeSize() {
        if (prizeNum == null) {
            prizeNum = new AtomicInteger(lottery.getPrizeSize());
        }

    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    @GetMapping("/api/now")
    public Result now() {
        return ResultUtils.success(System.currentTimeMillis());
    }

    /**
     * 暴露抽奖接口
     *
     * @return
     */
    @GetMapping("/api/exposer")
    public Result exposer(@RequestHeader(value = "token")String token) throws Exception {
        //解析token获取用户信息
        String userJson=TokenUtil.parseJWT(token).getSubject();
        JSONObject jsonObject=JSONObject.parseObject(userJson);
        User user=JSONObject.toJavaObject(jsonObject,User.class);
        return lotteryService.exposer(user);
    }
}
