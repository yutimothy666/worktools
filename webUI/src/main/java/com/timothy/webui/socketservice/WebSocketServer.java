package com.timothy.webui.socketservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/30 15:57
 */
@ServerEndpoint("/webSocket/{sid}")
@Component
public class WebSocketServer {
    public static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    public void sendMessage(Session session, String message) {
        if (session != null) {
            synchronized (session) {
                logger.info("发送数据: " + message);
                try {
                    session.getBasicRemote().sendText(message);
                    logger.info("发送信息 {}-{}", message, session);
                } catch (IOException e) {
                    logger.error("错误", e);
                }
            }
        }
    }

    public void sendMessage(String sid, String... strings) {
        try {
            sessionPools.get(sid).getBasicRemote().sendText(Arrays.toString(strings));
        } catch (IOException e) {
            logger.info("sendMessage error-", e);
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        sessionPools.put(sid, session);
        logger.info(sid + "连接");
        sendMessage(session, "open");
    }

    @OnClose
    public void onClose(Session session) {
        try {
            sendMessage(session, "clone");
        } catch (Exception e) {
            sendMessage(session, e.getStackTrace().toString());
            logger.info("error", e);
        }

    }

    @OnMessage
    public void onMessage(Session session, Byte message) {
        logger.info("-->" + message);
        sendMessage(session, message.toString());
    }
}
