package com.smart.server.queue.websokect;

import com.smart.client.SessionUser;
import com.smart.client.SessionUtils;
import com.smart.server.model.MsgRequest;
import com.smart.server.model.MsgResponse;
import com.smart.server.model.QueueModel;
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
 * 医生界面websocket连接类
 */

@ServerEndpoint(value = "/websocket/call", configurator = HttpSessionConfigurator.class)
public class CallSocket extends WebSocket {

    private static Map<Integer, List<Session>> clients = new ConcurrentHashMap<Integer, List<Session>>();

    private static final Logger logger = LoggerFactory.getLogger(CallSocket.class);

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        super.onOpen(clients, session, config);
    }

    /**
     * CALLED(诊断中)：获取当前诊断人信息
     * CALLING(呼叫)：获取下一个诊断人信息，并且广播所有页面（护士页面、医生界面、屏幕）
     * SKIP(跳过)：获取下一个诊断人信息，并且广播所有页面（护士页面、医生界面、屏幕）
     * RECALL(重呼): 广播屏幕
     * @param message
     * @param session
     * @return
     */
    @OnMessage
    public String onMessage(String message, Session session) {
        MsgRequest msgRequest = JsonUtils.parseObject(message, MsgRequest.class);
        SessionUser sessionUser = (SessionUser) session.getUserProperties().get(SessionUtils.SESSION_USER);
        QueueCollection queueCollection = QueueCollection.newInstance();
        if (RequestType.CALLED.equals(msgRequest.getType())) {
            return JsonUtils.toJSONString(new MsgResponse(RequestType.CALLED, queueCollection.getCalledInfo(sessionUser)));
        }
        else if (RequestType.CALLING.equals(msgRequest.getType())) {
            QueueModel queueModel = queueCollection.calling(sessionUser);
            if (queueModel != null) {
                CallBroadcast.listBroadcast(sessionUser, null);
            }
            CallBroadcast.screenBroadcast(sessionUser, queueModel);
            CallBroadcast.waitingBroadcast(sessionUser, session);
            return JsonUtils.toJSONString(new MsgResponse(RequestType.CALLING, queueCollection.getCalledInfo(sessionUser)));
        }
        else if(RequestType.SKIP.equals(msgRequest.getType())){
            QueueModel queueModel = queueCollection.skip(sessionUser);
            if (queueModel != null) {
                CallBroadcast.listBroadcast(sessionUser, null);
            }
            CallBroadcast.screenBroadcast(sessionUser, queueModel);
            CallBroadcast.waitingBroadcast(sessionUser, session);
            return JsonUtils.toJSONString(new MsgResponse(RequestType.SKIP, queueCollection.getCalledInfo(sessionUser)));
        }else if(RequestType.RECALL.equals(msgRequest.getType())){
            CallBroadcast.screenBroadcast(sessionUser, queueCollection.getCalledInfo(sessionUser).getInfo());
        }
        return JsonUtils.toJSONString(new MsgResponse(RequestType.UNKONW, null));
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