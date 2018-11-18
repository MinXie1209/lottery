package org.javatribe.lottery.po;

/**
 * @ClassName User
 * @Description 封装用户个人信息
 * @Author 江南小俊
 * @Date 2018/11/15 10:13
 * @Version 1.0.0
 **/
public class User {
    private String openid;
    private String nickname;
    private String headimgurl;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "User{" +
                "openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                '}';
    }
}
