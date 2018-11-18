package org.javatribe.lottery.enums;

/**
 * 返回数据
 */
public enum ResultEnum {
    UN_ERROR(-1, "未知错误"),
    SUCCESS(1, "成功"),
    ERROR(10000, "没有找到api"), NOT_PRIZE(10001, "没有抽奖活动"), NOT_LOGIN(10002, "您还没有登录");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
