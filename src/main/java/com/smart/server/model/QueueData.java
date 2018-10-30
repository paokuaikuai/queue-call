package com.smart.server.model;

import com.smart.mvc.model.PersistentObject;
import com.smart.server.queue.QueueItem;

import java.util.Date;

/**
 * 持久化队列模型
 *
 * @author YJ
 */
public class QueueData extends PersistentObject {

    private Integer departId;

    public QueueItem data;

    private Date createTime;

    public QueueData() {
    }

    public QueueData(Integer departId, QueueItem data, Date createTime) {
        this.departId = departId;
        this.data = data;
        this.createTime = createTime;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public QueueItem getData() {
        return data;
    }

    public void setData(QueueItem data) {
        this.data = data;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
