package com.smart.server.queue;

import com.smart.client.SessionUser;
import com.smart.mvc.util.SpringUtils;
import com.smart.server.common.Constant;
import com.smart.server.model.*;
import com.smart.server.service.BusinessTypeService;
import com.smart.server.service.DepartmentService;
import com.smart.server.service.QueueDataService;
import com.smart.server.service.RoomService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 队列数据操作
 * 操队列时（add,calling,skip）：将数据以json的形式保存到数据库中，重启服务恢复数据
 * @author YJ
 */
public class QueueCollection {

    public static Map<Object, QueueItem> data = new ConcurrentHashMap<Object, QueueItem>();

    private static QueueCollection queueCollection;

    private QueueCollection() {

    }

    /**
     * 创建单利实例
     * @return
     */
    public static QueueCollection newInstance() {
        if (queueCollection == null) {
            synchronized (QueueCollection.class) {
                if(queueCollection == null){
                    queueCollection = new QueueCollection();
                }
            }
        }
        return queueCollection;
    }

    /**
     * 待优化
     *
     * @param
     * @return
     */
    public QueueItem getQueueItem(SessionUser sessionUser) {
        if (data.containsKey(sessionUser.getDepartId())) {
            return data.get(sessionUser.getDepartId());
        } else {
            return getSynQueueItem(sessionUser);
        }
    }

    /**
     * 呼叫
     *
     * @return
     */
    public QueueModel calling(SessionUser sessionUser) {
        QueueItem queueItem = getQueueItem(sessionUser);
        QueueModel queueModel = queueItem.remove();

        List<QueueModel> queueModels = queueItem.getHisQueue().getData();
        for (QueueModel cm : queueModels) {
            if (cm.getDoctorId() == sessionUser.getUserId() && cm.getStatus().equals(Constant.VISITING)) {
                cm.setStatus(Constant.VISITED);
            }
        }

        if (queueModel == null) {
            return null;
        }

        RoomService roomService = SpringUtils.getBean(RoomService.class);
        Room room = roomService.get(sessionUser.getRoomId());

        queueModel.setStatus(Constant.VISITING);
        queueModel.setDoctorId(sessionUser.getUserId());
        queueModel.setDoctorName(sessionUser.getRealname());
        queueModel.setRoomId(sessionUser.getRoomId());
        queueModel.setRoomName(room.getRoomName());

        QueueDataService queueDataService = SpringUtils.getBean(QueueDataService.class);
        queueDataService.save(new QueueData(sessionUser.getDepartId(), getQueueItem(sessionUser), new Date()));

        return queueModel;
    }

    /**
     * 过号
     *
     * @return
     */
    public QueueModel skip(SessionUser sessionUser) {
        CalledModel calledModel = getCalledInfo(sessionUser);
        QueueModel queueModel = calledModel.getInfo();
        if (queueModel != null) {
            queueModel.setStatus(Constant.SKIP);
        }
        return queueCollection.calling(sessionUser);
    }

    /**
     * 添加
     */
    public void add(SessionUser sessionUser, QueueModel queueModel) {
        DepartmentService departmentService = SpringUtils.getBean(DepartmentService.class);
        queueModel.setDepartId(sessionUser.getDepartId());
        queueModel.setTypeName(getTypeName(queueModel));
        queueModel.setCreateTime(new Date());
        queueModel.setUpdateTime(new Date());
        queueModel.setStatus(Constant.UNVISIT);
        queueModel.setFirstNum(departmentService.get(sessionUser.getDepartId()).getFirstNum());

        getQueueItem(sessionUser).add(queueModel);

        QueueDataService queueDataService = SpringUtils.getBean(QueueDataService.class);
        queueDataService.save(new QueueData(sessionUser.getDepartId(), getQueueItem(sessionUser), new Date()));
    }

    /**
     * 获取队列类型
     * @param queueModel
     * @return
     */
    private String getTypeName(QueueModel queueModel) {
        try {
            return SpringUtils.getBean(BusinessTypeService.class).get(queueModel.getType()).getName();
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 查询医生当前信息
     */
    public CalledModel getCalledInfo(SessionUser sessionUser) {

        QueueItem queueItem = getQueueItem(sessionUser);
        List<QueueModel> queue = queueItem.getQueue().getData();
        List<QueueModel> hisQueue = queueItem.getHisQueue().getData();

        Integer diagnosed = 0;

        for (QueueModel queueModel : hisQueue) {
            if (queueModel.getDoctorId() == sessionUser.getUserId()) {
                diagnosed++;
            }
        }

        QueueModel call = null;
        for (int i = hisQueue.size() - 1; i >= 0; i--) {
            QueueModel queueModel = hisQueue.get(i);
            if (queueModel.getDoctorId() == sessionUser.getUserId() && queueModel.getStatus() == Constant.VISITING) {
                call = queueModel;
            }
        }
        CalledModel calledModel = new CalledModel();
        calledModel.setDiagnosed(diagnosed);
        calledModel.setWaiting(queue.size());
        calledModel.setInfo(call);
        return calledModel;
    }

    public ScreenModel getScreenInfo(SessionUser sessionUser, QueueModel queueModel) {

        QueueItem queueItem = getQueueItem(sessionUser);
        List<QueueModel> queue = queueItem.getQueue().getData();
        List<QueueModel> hisQueue = queueItem.getHisQueue().getData();
        List<QueueModel> calling = new ArrayList<QueueModel>();

        for (QueueModel ci : hisQueue) {
            if (ci.getStatus() == Constant.VISITING) {
                calling.add(ci);
            }
        }

        ScreenModel screenModel = new ScreenModel();
        screenModel.setQueue(queue);
        screenModel.setHisQueue(hisQueue);
        screenModel.setCalling(calling);
        screenModel.setSpeaking(queueModel);
        return screenModel;
    }

    private synchronized QueueItem getSynQueueItem(SessionUser sessionUser) {
        QueueDataService queueDataService = SpringUtils.getBean(QueueDataService.class);
        QueueData queueData = queueDataService.getByDepartId(sessionUser.getDepartId());
        QueueItem qi = new QueueItem();
        if (queueData != null) {
            qi = queueDataService.getByDepartId(sessionUser.getDepartId()).getData();
        }
        data.put(sessionUser.getDepartId(), qi);
        return qi;
    }

    /**
     * 清除内存数据，并且记录历史信息
     */
    public void clear() {
        QueueDataService queueDataService = SpringUtils.getBean(QueueDataService.class);
        try {
            List<QueueData> queueDataList = queueDataService.findAll();
            for (QueueData queueData : queueDataList) {
                List<QueueModel> data = new ArrayList<QueueModel>();
                data.addAll(queueData.getData().getQueue().getData());
                data.addAll(queueData.getData().getHisQueue().getData());
                queueDataService.saveQueueLog(data);
            }
        } finally {
            queueCollection = null;
            data.clear();
            queueDataService.deleteAll();
        }
    }
}
