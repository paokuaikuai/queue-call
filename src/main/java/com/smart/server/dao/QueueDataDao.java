package com.smart.server.dao;

import com.smart.mvc.dao.mybatis.Dao;
import com.smart.server.model.QueueData;
import java.util.List;

import com.smart.server.model.QueueModel;
import org.apache.ibatis.annotations.Param;

/**
 * 持久化队列数据接口
 *
 * @author YJ
 */
public interface QueueDataDao extends Dao<QueueData, Integer> {

    void deleteByDepartId(@Param("departId")Integer departId);

    QueueData getByDepartId(@Param("departId")Integer departId);

    List<QueueData> findAll();

    void deleteAll();

    void insertQueueLog(QueueModel queueModel);
}
