package app.dbService;

import app.dbService.dao.MessageDAO;
import app.dbService.dao.UserDAO;
import app.dbService.model.Message;
import app.dbService.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by alexa on 22.01.2017.
 */
public class DBServiceImpl implements DBService {
    private static DBServiceImpl ourInstance = new DBServiceImpl();
    private UserDAO userDAO;
    private MessageDAO messageDAO;

    public static DBServiceImpl instance() {
        return ourInstance;
    }


    private DBServiceImpl() {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/spring-hibernate.xml");
        messageDAO = (MessageDAO) context.getBean("messageDAO");
        userDAO = (UserDAO) context.getBean("userDAO");
    }

    @Override
    public long addUser(User user) {
        userDAO.insert(user);
        return userDAO.getByEmail(user.getEmail()).getId();
    }

    @Override
    public User getUserByLogin(String login) {
        return userDAO.getByLogin(login);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getByEmail(email);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.delete(user);
    }

    @Override
    public void addMessage(User user, Date date, String text) {
        Message message = new Message(user.getId(),date,text);
        messageDAO.insert(message);
    }

    @Override
    public User getUserById(long id) {
        return userDAO.get(id);
    }

    @Override
    public List<Message> getMessages() {
        return messageDAO.getMessages();
    }

}
