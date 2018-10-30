package com.smart.server.model;

public class MsgRequest {
    private String type;
    private QueueModel data;

    public MsgRequest(){}

    public MsgRequest(String type, QueueModel data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public QueueModel getData() {
        return data;
    }

    public void setData(QueueModel data) {
        this.data = data;
    }
}
