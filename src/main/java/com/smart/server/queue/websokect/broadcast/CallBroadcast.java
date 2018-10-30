package com.smart.server.queue.websokect.broadcast;

import com.smart.client.SessionUser;
import com.smart.server.model.MsgResponse;
import com.smart.server.model.QueueModel;
import com.smart.server.queue.QueueCollection;
import com.smart.server.utils.JsonUtils;
import com.smart.server.queue.websokect.CallSocket;
import com.smart.server.queue.websokect.QueueSocket;
import com.smart.server.queue.websokect.ScreenSocket;
import com.smart.server.queue.websokect.config.RequestType;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * websocket广播
 * 通知界面更新数据
 */
public class CallBroadcast {

    //广播添加页面(filterSession = null,不广播自己)
    public static void listBroadcast(SessionUser sessionUser, Session filterSession) {
        List<Session> sessions = getBroadcastSession(QueueSocket.getClients(), sessionUser, filterSession);
        broadcast(sessions, new MsgResponse(RequestType.LIST, QueueCollection.newInstance().getQueueItem(sessionUser)));
    }

    /**
     * 广播排队人数
     *
     * @param sessionUser
     */
    public static void waitingBroadcast(SessionUser sessionUser, Session filterSession) {
        List<Session> sessions = getBroadcastSession(CallSocket.getClients(), sessionUser, filterSession);
        broadcast(sessions, new MsgResponse(RequestType.WAITING, QueueCollection.newInstance().getQueueItem(sessionUser).getQueue().size()));
    }

    /**
     * 广播屏幕
     *
     * @param sessionUser
     */
    public static void screenBroadcast(SessionUser sessionUser, QueueModel queueMode) {
        List<Session> sessions = getBroadcastSession(ScreenSocket.getClients(), sessionUser, null);
        broadcast(sessions, new MsgResponse(RequestType.SCREEN, QueueCollection.newInstance().getScreenInfo(sessionUser, queueMode)));
    }

    /**
     *
     * @param clients session列表
     * @param sessionUser 当前用户信息
     * @param filterSession 过滤的session
     * @return
     */
    public static List<Session> getBroadcastSession(Map<Integer, List<Session>> clients, SessionUser sessionUser, Session filterSession) {
        List<Session> cs = clients.get(sessionUser.getDepartId());
        if (filterSession == null) {
            return cs;
        }
        List<Session> sessions = new ArrayList<Session>();
        for (Session s : cs) {
            if (!filterSession.getId().equals(s.getId())) {
                sessions.add(s);
            }
        }
        return sessions;
    }

    /**
     * 将数据传回客户端
     * 异步的方式
     *
     * @param type
     * @param message
     */
    public static void broadcast(Map<Integer, List<Session>> clients, Integer type, String message) {
        if (!clients.containsKey(type)) {
            throw new NullPointerException("[" + type + "]Connection does not exist");
        }
        broadcast(clients.get(type), message);
    }

    /**
     * 广播
     * @param sessions
     * @param object
     */
    public static void broadcast(List<Session> sessions, Object object) {
        if(sessions == null){
            return;
        }
        for (Session session : sessions) {
            broadcast(session, JsonUtils.toJSONString(object));
        }
    }

    /**
     * 广播
     * @param sessions
     * @param message
     */
    public static void broadcast(List<Session> sessions, String message) {
        if(sessions == null){
            return;
        }
        for (Session session : sessions) {
            broadcast(session, message);
        }
    }

    /**
     * 广播
     * @param session
     * @param object
     */
    public static void broadcast(Session session, Object object) {
        broadcast(session, JsonUtils.toJSONString(object));
    }

    /**
     * 广播
     * @param session
     * @param message
     */
    public static void broadcast(Session session, String message) {
        if (session == null || !session.isOpen()) {
            return;
        }
        session.getAsyncRemote().sendText(message);
    }
}
