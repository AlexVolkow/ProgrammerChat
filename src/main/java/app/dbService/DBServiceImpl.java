package app.dbService;

import app.dbService.dao.MessageDAO;
import app.dbService.dao.RoomDAO;
import app.dbService.dao.UserDAO;
import app.dbService.model.Message;
import app.dbService.model.Room;
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
    private ApplicationContext context;

    public static DBServiceImpl instance() {
        return ourInstance;
    }

    private DBServiceImpl() {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/spring-hibernate.xml");
    }

    @Override
    public long addUser(User user) {
        UserDAO userDAO = (UserDAO)context.getBean("userDAO");
        userDAO.insert(user);
        return userDAO.getByEmail(user.getEmail()).getId();
    }

    @Override
    public User getUserByLogin(String login) {
        UserDAO userDAO = (UserDAO)context.getBean("userDAO");
        return userDAO.getByLogin(login);
    }

    @Override
    public User getUserByEmail(String email) {
        UserDAO userDAO = (UserDAO)context.getBean("userDAO");
        return userDAO.getByEmail(email);
    }

    @Override
    public void deleteUser(User user) {
        UserDAO userDAO = (UserDAO)context.getBean("userDAO");
        userDAO.delete(user);
    }

    @Override
    public long addRoom(Room room) {
        RoomDAO roomDAO = (RoomDAO) context.getBean("roomDAO");
        roomDAO.insert(room);
        return roomDAO.getByName(room.getName()).getId();
    }

    @Override
    public void deleteRoom(Room room) {
        RoomDAO roomDAO = (RoomDAO) context.getBean("roomDAO");
        roomDAO.delete(room);
    }

    @Override
    public List<Room> allRooms() {
        RoomDAO roomDAO = (RoomDAO) context.getBean("roomDAO");
        return roomDAO.getAll();
    }

    @Override
    public void addMessage(User user, Room room, Date date, String text) {
        MessageDAO messageDAO = (MessageDAO) context.getBean("messageDAO");
        Message message = new Message(user.getId(),room.getId(),date,text);
        messageDAO.insert(message);
    }

    @Override
    public List<Message> getMessageByRoom(Room room) {
        MessageDAO messageDAO = (MessageDAO) context.getBean("messageDAO");
        return messageDAO.getMessageByRoomId(room.getId());
    }
}
