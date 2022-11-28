package com.qfedu.fmmall.WebSocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component//接收spring的管理
@ServerEndpoint("/webSocket/{oid}")//请求路径,把订单号传过来
public class WebSocketServer {

    private static ConcurrentHashMap<String,Session> sessionMap = new ConcurrentHashMap<>();


    /**
     * 前端发送请求，简历websocket连接，就会执行   @OnOpen方法
     */
    @OnOpen
    public void open(@PathParam("oid") String orderId, Session session){
        System.out.println("websocket已经启动"+orderId);
        sessionMap.put(orderId,session);
    }

    /**
     * 前端关闭页面或者主动关闭websocket连接 ，都会执行close
     */
    @OnClose
    public void close(@PathParam("oid") String orderId){
        sessionMap.remove(orderId);
    }

    public static void sendMsg(String orderId,String msg){

        try {
            Session session = sessionMap.get(orderId);
            session.getBasicRemote().sendText(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
