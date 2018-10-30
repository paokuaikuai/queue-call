package com.smart.server.queue.websokect;

import com.alibaba.fastjson.JSON;
import com.smart.client.SessionUser;
import com.smart.client.SessionUtils;
import com.smart.server.model.MsgRequest;
import com.smart.server.model.MsgResponse;
import com.smart.server.queue.QueueCollection;
import com.smart.server.utils.JsonUtils;
import com.smart.server.queue.websokect.broadcast.CallBroadcast;
import com.smart.server.queue.websokect.config.HttpSessionConfigurator;
import com.smart.server.queue.websokect.config.RequestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 护士websocket连接类
 */
@ServerEndpoint(value = "/websocket/queue", configurator = HttpSessionConfigurator.class)
public class QueueSocket extends WebSocket {

    private static final Logger logger = LoggerFactory.getLogger(QueueSocket.class);

    private static Map<Integer, List<Session>> clients = new ConcurrentHashMap<Integer, List<Session>>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        super.onOpen(clients, session, config);
    }

    /**
     * 请求类型
     *  ADD：添加诊断人信息，并且广播所有页面（护士页面、医生界面、屏幕）
     *  List: 获取队列信息
     * @param message
     * @param session
     * @return
     */
    @OnMessage
    public String onMessage(String message, Session session) {
        MsgRequest msgRequest = JSON.parseObject(message, MsgRequest.class);
        SessionUser sessionUser = (SessionUser) session.getUserProperties().get(SessionUtils.SESSION_USER);
        QueueCollection queueCollection = QueueCollection.newInstance();
        //添加操作
        if (RequestType.ADD.equals(msgRequest.getType())) {
            queueCollection.add(sessionUser, msgRequest.getData());
            CallBroadcast.listBroadcast(sessionUser, session);
            CallBroadcast.waitingBroadcast(sessionUser, null);
            CallBroadcast.screenBroadcast(sessionUser, null);
            return JsonUtils.toJSONString(new MsgResponse(RequestType.ADD, queueCollection.getQueueItem(sessionUser)));
        }
        return JsonUtils.toJSONString(new MsgResponse(RequestType.LIST, queueCollection.getQueueItem(sessionUser)));
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error("error", throwable);
    }

    @OnClose
    public void onClose(Session session) {
        clearClosedSession(clients);
    }

    public static Map<Integer, List<Session>> getClients() {
        return clients;
    }
}