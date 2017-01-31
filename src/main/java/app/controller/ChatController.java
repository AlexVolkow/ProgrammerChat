package app.controller;

import app.chat.SimpleMessage;
import app.chat.StatusMessage;
import app.dbService.model.Message;
import app.service.AccountService;
import app.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexa on 30.01.2017.
 */
@Controller
public class ChatController {
    private AccountService accountService = AccountService.instance();

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/chat",method = RequestMethod.GET)
    public String showChatPage(UsernamePasswordAuthenticationToken token,Model model){
        accountService.addSession(RequestContextHolder.getRequestAttributes().getSessionId(),
                accountService.getUserByLogin(token.getName()));
        List<Message> messages = messageService.getMessages();
        List<SimpleMessage> res = new ArrayList<>();
        for (Message message:messages){
            String login = accountService.getUser(message.getUserId()).getLogin();
            SimpleMessage simpleMessage =
                    new SimpleMessage(login,message.getDate(),message.getText(),StatusMessage.USER_MESSAGE);
            res.add(simpleMessage);
        }
        model.addAttribute("messages",res);
        return "chat";
    }
}
