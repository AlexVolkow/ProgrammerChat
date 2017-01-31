package app.service;

import app.chat.ChatWebSocket;
import app.chat.SimpleMessage;
import app.chat.StatusMessage;
import app.dbService.DBService;
import app.dbService.DBServiceImpl;
import app.dbService.model.Message;
import app.dbService.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by alexa on 30.01.2017.
 */
@Service
public class MessageService {
    private DBService dbService = DBServiceImpl.instance();
    private Set<ChatWebSocket> webSockets;

    public MessageService() {
        this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    public void sendMessage(User user, Date date, String text, StatusMessage statusMessage) {
        String data = "";
        try {
            if (statusMessage == StatusMessage.USER_MESSAGE) {
                dbService.addMessage(user, date, text);
                data = wrapJSON(user.getLogin(), date, text, statusMessage);
            } else {
                data = wrapJSON("*", date, text, statusMessage);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        for (ChatWebSocket web : webSockets) {
            try {
                web.sendString(data);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String wrapJSON(String login, Date date, String text, StatusMessage statusMessage) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("HH:mm dd-MM-yyy");
        mapper.setDateFormat(df);
        SimpleMessage message = new SimpleMessage(login, date, text, statusMessage);
        return mapper.writeValueAsString(message);
    }

    public List<Message> getMessages() {
        return dbService.getMessages();
    }

    public void add(ChatWebSocket webSocket) {
        webSockets.add(webSocket);
    }

    public void remove(ChatWebSocket webSocket) {
        webSockets.remove(webSocket);
    }
}
