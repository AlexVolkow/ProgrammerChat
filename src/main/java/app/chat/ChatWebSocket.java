package app.chat;

import app.chat.message.ChatMessage;
import app.chat.message.InfoMessage;
import app.chat.message.StatusMessage;
import app.dbService.model.Message;
import app.dbService.model.User;
import app.service.AccountService;
import app.service.MessageService;
import  org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

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

    private User user;

    public ChatWebSocket(MessageService messageService, AccountService accountService,String sessionId) {
        this.messageService = messageService;
        this.accountService = accountService;
        this.sessionId = sessionId;
        this.user = accountService.getUserBySessionId(sessionId);
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        messageService.add(sessionId,this);
        this.session = session;
        messageService.sendMessage(new InfoMessage(new Date(),user.getLogin()+" joined"));
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        ChatMessage message = new ChatMessage(new Message(user.getId(),new Date(),data),user.getLogin(), StatusMessage.USER_MESSAGE);
        messageService.sendMessage(message);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        messageService.remove(sessionId);
        messageService.sendMessage(new InfoMessage(new Date(),user.getLogin()+" left"));
    }

    public void sendString(String data) {
        try {
            session.getRemote().sendString(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}