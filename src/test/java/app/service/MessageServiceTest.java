package app.service;

import app.chat.StatusMessage;
import app.dbService.model.Message;
import app.dbService.model.User;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by alexa on 30.01.2017.
 */
public class MessageServiceTest {
    @Test
    public void addMessage() throws Exception {
        MessageService messageService = new MessageService();
        AccountService accountService = AccountService.instance();

        User user = accountService.getUserByLogin("alex");

        messageService.sendMessage(user,new Date(),"It's alive", StatusMessage.USER_MESSAGE);
        messageService.sendMessage(user,new Date(),"Hello world!",StatusMessage.USER_MESSAGE);

        List<Message> messageList = messageService.getMessages();
        for (Message message:messageList){
            System.out.println(message);
        }
    }

}