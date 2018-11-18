package org.javatribe.lottery.service.impl;

import org.javatribe.lottery.enums.ResultEnum;
import org.javatribe.lottery.mapper.PrizeMapper;
import org.javatribe.lottery.po.Exposer;
import org.javatribe.lottery.po.Prize;
import org.javatribe.lottery.po.Result;
import org.javatribe.lottery.po.User;
import org.javatribe.lottery.service.LotteryService;
import org.javatribe.lottery.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @ClassName LotteryServiceImpl
 * @Description 抽奖业务实现
 * @Author 江南小俊
 * @Date 2018/11/15 13:43
 * @Version 1.0.0
 **/
@Service
public class LotteryServiceImpl implements LotteryService {
    @Autowired
    PrizeMapper prizeMapper;
    /**
     * 传入用户信息，获取暴露信息
     * @param user
     * @return
     */
    @Override
    public Result exposer(User user) {
        /**
         * 1. 获取数据库抽奖的开始时间和结束时间
         * 2.判断系统当前时间
         * 3.如果不在时间范围内，不暴露抽奖接口
         * 4.如果在抽奖允许访问内，暴露接口
         */
        List<Prize> prizes=prizeMapper.selectByExample(null);
        if(prizes.size()>0){
            Prize prize=prizes.get(0);
            Date startTime=prize.getStartTime();
            Date endTime=prize.getEndTime();
            Date now=new Date();
            if(now.getTime()<startTime.getTime()||now.getTime()>endTime.getTime()){
                    return ResultUtils.success(new Exposer(false,startTime.getTime(),endTime.getTime(),now.getTime()));
            }
            else{
                String md5=getMd5(user.getOpenid());
                return ResultUtils.success(new Exposer(true,md5));
            }
        }
        return ResultUtils.error(ResultEnum.NOT_PRIZE);
    }

    private String getMd5(String openid) {
        String salt="_(^D*H(^Q#E#B)BND&3Gb";
        String base=openid+salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    public void lottery() {

    }
}
