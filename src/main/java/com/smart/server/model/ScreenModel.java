package com.smart.server.model;

import java.util.List;

public class ScreenModel {

    private List<QueueModel> queue;
    private List<QueueModel> hisQueue;
    private List<QueueModel> calling;

    private QueueModel speaking;

    public List<QueueModel> getQueue() {
        return queue;
    }

    public void setQueue(List<QueueModel> queue) {
        this.queue = queue;
    }

    public List<QueueModel> getHisQueue() {
        return hisQueue;
    }

    public void setHisQueue(List<QueueModel> hisQueue) {
        this.hisQueue = hisQueue;
    }

    public List<QueueModel> getCalling() {
        return calling;
    }

    public void setCalling(List<QueueModel> calling) {
        this.calling = calling;
    }

    public QueueModel getSpeaking() {
        return speaking;
    }

    public void setSpeaking(QueueModel speaking) {
        this.speaking = speaking;
    }
}
