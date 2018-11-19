package org.javatribe.lottery.exception;
import org.javatribe.lottery.enums.ResultEnum;

/**
 * @author 江南小俊
 * @create 2018-08-30
 * @desc 自定义异常
 **/
public class ResultException extends Exception {
    private Integer code;

    public ResultException(ResultEnum error) {
        super(error.getMsg());
        this.code = error.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
