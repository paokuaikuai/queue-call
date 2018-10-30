package com.smart.server.model;

public class CalledModel {
    private Integer diagnosed;

    private Integer waiting;

    private QueueModel info;

    public Integer getDiagnosed() {
        return diagnosed;
    }

    public void setDiagnosed(Integer diagnosed) {
        this.diagnosed = diagnosed;
    }

    public Integer getWaiting() {
        return waiting;
    }

    public void setWaiting(Integer waiting) {
        this.waiting = waiting;
    }

    public QueueModel getInfo() {
        return info;
    }

    public void setInfo(QueueModel info) {
        this.info = info;
    }
}
