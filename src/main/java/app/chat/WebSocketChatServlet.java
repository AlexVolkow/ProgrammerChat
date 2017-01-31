package app.chat;

import app.service.AccountService;
import app.service.MessageService;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * Created by alexa on 31.01.2017.
 */

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "WebSocketChatServlet", urlPatterns = {"/listen"})
public class WebSocketChatServlet extends WebSocketServlet {
    private final static int LOGOUT_TIME = 10 * 60 * 1000;
    private final MessageService messageService;

    private AccountService accountService = AccountService.instance();

    public WebSocketChatServlet() {
        this.messageService = new MessageService();
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((req, resp) -> new ChatWebSocket(messageService,accountService,req.getSession().getId()));
    }
}