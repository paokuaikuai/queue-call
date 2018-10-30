package com.smart.server.queue.scheduler;

import com.smart.server.queue.QueueCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class QueueTask {

    private static final Logger logger = LoggerFactory.getLogger(QueueTask.class);

    //每天晚上12点清除数据
    @Scheduled(cron = "0 0 0 * * ?")
    public void clearQueue() {
        logger.info("start clear queue...");
        QueueCollection.newInstance().clear();
        logger.info("end clear queue...");
    }
}
