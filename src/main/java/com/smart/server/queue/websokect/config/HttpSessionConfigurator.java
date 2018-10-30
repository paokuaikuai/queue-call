package com.smart.server.queue.websokect.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

/**
 * 在websocket session中存放用户信息
 */
public class HttpSessionConfigurator extends Configurator {

    private static final Logger logger = LoggerFactory.getLogger(HttpSessionConfigurator.class);

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        HttpSession session = (HttpSession) request.getHttpSession();//session有可能为空
        if (session!=null){
            sec.getUserProperties().put(HttpSession.class.getName(),session);
        }else{
            logger.info("modifyHandshake 获取到null session");
        }
    }
}
