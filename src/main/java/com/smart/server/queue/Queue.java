package com.smart.server.queue;


import com.smart.server.model.QueueModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 队列
 *
 * @author YJ
 */
public class Queue {

    private List<QueueModel> data = new ArrayList<QueueModel>();

    /**
     * 入队
     */
    public void add(QueueModel queueData) {
        data.add(queueData);
    }

    /**
     * 出队
     */
    public QueueModel remove() {
        if (data.size() > 0) {
            return data.remove(0);
        }
        return null;
    }

    /**
     * 大小
     * @return
     */
    public int size() {
        return data.size();
    }

    public List<QueueModel> getData() {
        return data;
    }

    public void setData(List<QueueModel> data) {
        this.data = data;
    }
}