package com.smart.server.queue.websokect;

import com.smart.client.SessionUser;
import com.smart.client.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class WebSocket {

    private static final Logger logger = LoggerFactory.getLogger(WebSocket.class);

    /**
     * 保存连接的session,主要用于广播
     * @param clients
     * @param session
     * @param config
     */
    public void onOpen(Map<Integer, List<Session>> clients, Session session, EndpointConfig config) {
        SessionUser sessionUser = getSessionUser(config);
        logger.info("Websocket Start Connecting:DepartId=" + sessionUser.getDepartId());
        if (!clients.containsKey(sessionUser.getDepartId())) {
            clients.put(sessionUser.getDepartId(), new ArrayList<Session>());
        }
        session.getUserProperties().put(SessionUtils.SESSION_USER, getSessionUser(config));
        clients.get(sessionUser.getDepartId()).add(session);
    }

    /**
     * 获取当前医生信息
     * @param config
     * @return
     */
    private static SessionUser getSessionUser(EndpointConfig config) {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        SessionUser sessionUser = null;
        if (httpSession != null) {
            sessionUser = (SessionUser) httpSession.getAttribute(SessionUtils.SESSION_USER);
        }
        return sessionUser;
    }

    /**
     * 清理无用的session
     * @param clients
     */
    protected static void clearClosedSession(Map<Integer, List<Session>> clients) {
        for (Map.Entry<Integer,List<Session>> kv : clients.entrySet()){
            Iterator<Session> it = kv.getValue().iterator();
            while (it.hasNext()) {
                Session s = it.next();
                if(!s.isOpen()){
                    it.remove();
                }
            }
        }
    }
}
