package app.chat;

import app.dbService.model.User;
import app.service.AccountService;
import app.service.MessageService;
import  org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by alexa on 31.01.2017.
 */

@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class ChatWebSocket {
    private MessageService messageService;

    private AccountService accountService;

    private Session session;

    private String sessionId;

    public ChatWebSocket(MessageService messageService, AccountService accountService,String sessionId) {
        this.messageService = messageService;
        this.accountService = accountService;
        this.sessionId = sessionId;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        messageService.add(this);
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        User user = accountService.getUserBySessionId(sessionId);
        messageService.sendMessage(user,new Date(),data,StatusMessage.USER_MESSAGE);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        messageService.remove(this);
    }

    public void sendString(String data) {
        try {
            session.getRemote().sendString(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}