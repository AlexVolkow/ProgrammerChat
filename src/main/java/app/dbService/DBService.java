package app.dbService;

import app.dbService.model.Message;
import app.dbService.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by alexa on 22.01.2017.
 */
public interface DBService {
    long addUser(User user);
    void deleteUser(User user);
    User getUserByLogin(String login);
    User getUserByEmail(String email);
    User getUserById(long id);

    void addMessage(User user, Date date, String text);
    List<Message> getMessages();
}
