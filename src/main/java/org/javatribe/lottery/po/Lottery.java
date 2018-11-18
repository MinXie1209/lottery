package org.javatribe.lottery.po;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName Lottery
 * @Description 奖池参数
 * @Author 江南小俊
 * @Date 2018/11/15 10:48
 * @Version 1.0.0
 **/
@Component("lottery")
public class Lottery {
    /**
     * 奖池大小
     */
    @Value("${prizePool.prizeSize}")
    private int prizeSize;
    /**
     * 特等奖和一等奖的界线
     */
    @Value("${prizePool.specialAndFirstLine}")
    private int specialAndFirstLine;
    /**
     *  一二等奖界线
     */
    @Value("${prizePool.firstAndSecondLine}")
    private int firstAndSecondLine;
    /**
     *  二三等奖界线
     */
    @Value("${prizePool.secondAndThirdLine}")
    private int secondAndThirdLine;

    public int getPrizeSize() {
        return prizeSize;
    }

    public void setPrizeSize(int prizeSize) {
        this.prizeSize = prizeSize;
    }

    public int getSpecialAndFirstLine() {
        return specialAndFirstLine;
    }

    public void setSpecialAndFirstLine(int specialAndFirstLine) {
        this.specialAndFirstLine = specialAndFirstLine;
    }

    public int getFirstAndSecondLine() {
        return firstAndSecondLine;
    }

    public void setFirstAndSecondLine(int firstAndSecondLine) {
        this.firstAndSecondLine = firstAndSecondLine;
    }

    public int getSecondAndThirdLine() {
        return secondAndThirdLine;
    }

    public void setSecondAndThirdLine(int secondAndThirdLine) {
        this.secondAndThirdLine = secondAndThirdLine;
    }
}
