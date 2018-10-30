package com.smart.server.queue;

import com.smart.server.model.QueueModel;

/**
 * 队列元数据
 *
 * @author YJ
 */
public class QueueItem {

    private Queue queue = new Queue();

    private Queue hisQueue = new Queue();

    public synchronized void add(QueueModel queueModel) {
        queue.add(queueModel);
        queueModel.setNumber(queue.size() + hisQueue.size());
    }

    /**
     * 出队
     */
    public synchronized QueueModel remove() {
        QueueModel queueModel = null;
        if (queue.size() > 0) {
            queueModel = queue.remove();
            hisQueue.add(queueModel);
        }
        return queueModel;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public Queue getHisQueue() {
        return hisQueue;
    }

    public void setHisQueue(Queue hisQueue) {
        this.hisQueue = hisQueue;
    }
}
