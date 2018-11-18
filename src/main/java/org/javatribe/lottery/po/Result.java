package org.javatribe.lottery.po;

import java.io.Serializable;

/**
 * @Auther: JNXJ
 * @Date: 2018/10/20 19:40
 * @Description:封装返回数据
 */
public class Result<T> implements Serializable {
    private Integer code=1;
    private String msg="成功";
    private T data=null;

    public Result(T data) {
        this.msg="成功";
        this.data = data;
    }

    public Result(String msg) {
        this.msg = msg;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
