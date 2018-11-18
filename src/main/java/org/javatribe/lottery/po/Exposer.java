package org.javatribe.lottery.po;

/**
 * @ClassName Exposer
 * @Description 封装暴露接口
 * @Author 江南小俊
 * @Date 2018/11/18 15:22
 * @Version 1.0.0
 **/
public class Exposer {
    private boolean exposer;
    private long startTime;
    private long endTime;
    private long now;
    private String md5;

    public Exposer() {
    }

    public Exposer(boolean exposer, long startTime, long endTime, long now, String md5) {
        this.exposer = exposer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.now = now;
        this.md5 = md5;
    }

    public Exposer(boolean exposer, long startTime, long endTime, long now) {
        this.exposer = exposer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.now = now;
    }

    public Exposer(boolean exposer, String md5) {
        this.exposer = exposer;
        this.md5 = md5;
    }

    public boolean isExposer() {
        return exposer;
    }

    public void setExposer(boolean exposer) {
        this.exposer = exposer;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
