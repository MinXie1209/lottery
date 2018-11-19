package org.javatribe.lottery.service;

import org.javatribe.lottery.po.Result;
import org.javatribe.lottery.po.User;
import org.javatribe.lottery.po.WinPrize;

import java.util.List;

/**
 * 抽奖业务接口
 */
public interface LotteryService {
     /**
      * 传入用户信息，获取暴露信息
      * @param user
      * @return
      */
     Result exposer(User user);
     int lottery(WinPrize winPrize);

     Result listPrize();

    /**
     * 校验MD5
     * @param md5
     * @param user
     * @return
     */
    boolean checkMD5(String md5, User user);
}
