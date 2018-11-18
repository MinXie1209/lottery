package org.javatribe.lottery.service;

import org.javatribe.lottery.po.Result;
import org.javatribe.lottery.po.User;

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
     void lottery();
}
