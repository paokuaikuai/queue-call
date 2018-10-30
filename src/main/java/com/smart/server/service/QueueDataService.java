package com.smart.server.service;


import com.smart.mvc.service.mybatis.Service;
import com.smart.server.model.QueueData;
import com.smart.server.model.QueueModel;

import java.util.List;

/**
 * 持久化队数据服务
 *
 * @author YJ
 */
public interface QueueDataService extends Service<QueueData, Integer> {
    void deleteByDepartId(Integer departId);

    QueueData getByDepartId(Integer departId);

    List<QueueData> findAll();

    void deleteAll();

    void saveQueueLog(List<QueueModel> queueModels);
}
