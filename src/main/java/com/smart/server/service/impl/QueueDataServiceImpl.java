package com.smart.server.service.impl;

import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.server.dao.QueueDataDao;
import com.smart.server.model.QueueData;
import com.smart.server.model.QueueModel;
import com.smart.server.service.QueueDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 队列持久化实现类
 *
 * @author YJ
 */
@Service
public class QueueDataServiceImpl extends ServiceImpl<QueueDataDao, QueueData, Integer> implements QueueDataService {

    @Autowired
    public void setDao(QueueDataDao dao) {
        this.dao = dao;
    }


    public void deleteByDepartId(Integer departId) {
        dao.deleteByDepartId(departId);
    }

    public QueueData getByDepartId(Integer departId) {
        return dao.getByDepartId(departId);
    }

    public List<QueueData> findAll() {
        return dao.findAll();
    }

    public void deleteAll() {
        dao.deleteAll();
    }

    public void saveQueueLog(List<QueueModel> queueModels){
        for (QueueModel queueModel : queueModels){
            dao.insertQueueLog(queueModel);
        }
    }
    @Override
    public void save(QueueData queueData) {
        dao.deleteByDepartId(queueData.getDepartId());
        super.save(queueData);
    }
}
