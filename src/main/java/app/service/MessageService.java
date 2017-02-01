package app.service;

import app.chat.ChatWebSocket;
import app.chat.message.ChatMessage;
import app.chat.message.StatusMessage;
import app.dbService.DBService;
import app.dbService.DBServiceImpl;
import app.dbService.model.Message;
import app.dbService.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by alexa on 30.01.2017.
 */
@Service
public class MessageService {
    private DBService dbService = DBServiceImpl.instance();
    private AccountService accountService = AccountService.instance();
    private Map<String, ChatWebSocket> webSockets;

    public MessageService() {
        this.webSockets = new ConcurrentHashMap<>();
    }

    public void sendMessage(ChatMessage chatMessage) {
        if (chatMessage.getStatus() == StatusMessage.USER_MESSAGE) {
            dbService.addMessage(chatMessage.getUserId(), chatMessage.getDate(), chatMessage.getText());
        }
        for (String sessionId : webSockets.keySet()) {
            User user = accountService.getUserBySessionId(sessionId);
            if (Filter.apply(user, chatMessage)) {
                try {
                    if (isMe(user,chatMessage)){
                        chatMessage.setStatus(StatusMessage.ME);
                    }
                    String data =
                            wrapJSON(chatMessage.getLogin(), chatMessage.getDate(), chatMessage.getText(), chatMessage.getStatus());
                    webSockets.get(sessionId).sendString(data);
                } catch (JsonProcessingException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private boolean isMe(User user, ChatMessage message){
        if (message.getStatus()==StatusMessage.USER_MESSAGE && user.getLogin().equals(message.getLogin())){
            return true;
        }
        return false;
    }
    private String wrapJSON(String login, Date date, String text, StatusMessage statusMessage) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("HH:mm dd-MM-yyy");
        Map<String, String> message = new HashMap<>();
        message.put("login", login);
        message.put("date", df.format(date));
        message.put("text", text);
        message.put("status", statusMessage.toString());
        return mapper.writeValueAsString(message);
    }

    public List<Message> getMessages() {
        return dbService.getMessages();
    }

    public void add(String sessionId, ChatWebSocket webSocket) {
        webSockets.put(sessionId, webSocket);
    }

    public void remove(String sessionId) {
        webSockets.remove(sessionId);
    }
}
