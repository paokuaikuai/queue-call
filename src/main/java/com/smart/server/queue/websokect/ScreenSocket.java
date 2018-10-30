package com.smart.server.queue.websokect;

import com.smart.client.SessionUser;
import com.smart.client.SessionUtils;
import com.smart.server.model.MsgResponse;
import com.smart.server.queue.QueueCollection;
import com.smart.server.utils.JsonUtils;
import com.smart.server.queue.websokect.config.HttpSessionConfigurator;
import com.smart.server.queue.websokect.config.RequestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 屏幕websocket连接
 */
@ServerEndpoint(value = "/websocket/screen", configurator = HttpSessionConfigurator.class)
public class ScreenSocket extends WebSocket {

    private static final Logger logger = LoggerFactory.getLogger(ScreenSocket.class);

    private static Map<Integer, List<Session>> clients = new ConcurrentHashMap<Integer, List<Session>>();

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        super.onOpen(clients, session, config);
    }

    /**
     * 接收呼叫信息
     * @param message
     * @param session
     * @return
     */
    @OnMessage
    public String onMessage(String message, Session session) {
        SessionUser sessionUser = (SessionUser) session.getUserProperties().get(SessionUtils.SESSION_USER);
        return JsonUtils.toJSONString(new MsgResponse(RequestType.SCREEN, QueueCollection.newInstance().getScreenInfo(sessionUser, null)));
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
