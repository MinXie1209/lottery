package org.javatribe.lottery.service.impl;

import org.javatribe.lottery.enums.ResultEnum;
import org.javatribe.lottery.mapper.PrizeMapper;
import org.javatribe.lottery.mapper.WinPrizeMapper;
import org.javatribe.lottery.po.*;
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
    @Autowired
    WinPrizeMapper winPrizeMapper;

    /**
     * 传入用户信息，获取暴露信息
     *
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
        List<Prize> prizes = prizeMapper.selectByExample(null);
        if (prizes.size() > 0) {
            Prize prize = prizes.get(0);
            Date startTime = prize.getStartTime();
            Date endTime = prize.getEndTime();
            Date now = new Date();
            if (now.getTime() < startTime.getTime() || now.getTime() > endTime.getTime()) {
                return ResultUtils.success(new Exposer(false, startTime.getTime(), endTime.getTime(), now.getTime()));
            } else {
                String md5 = generateMD5(user.getOpenid());
                return ResultUtils.success(new Exposer(true, md5));
            }
        }
        return ResultUtils.error(ResultEnum.NOT_PRIZE);
    }


    private String generateMD5(String openid) {
        String salt = "_(^D*H(^Q#E#B)BND&3Gb";
        String base = openid + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    public int lottery(WinPrize winPrize) {
        try {
            return winPrizeMapper.insert(winPrize);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Result listPrize() {
        WinPrizeExample example = new WinPrizeExample();
        example.setOrderByClause("id desc");
        return ResultUtils.success(winPrizeMapper.selectByExample(example));
    }

    /**
     * 校验MD5
     *
     * @param md5
     * @param user
     * @return
     */
    @Override
    public boolean checkMD5(String md5, User user) {
        if (md5 == null || user == null){
            return false;
        }
        return md5.equals(generateMD5(user.getOpenid()));
    }

}
