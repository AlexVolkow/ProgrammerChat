package app.dbService;

import app.dbService.model.Message;
import app.dbService.model.Room;
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

    long addRoom(Room room);
    void deleteRoom(Room room);
    List<Room> allRooms();

    void addMessage(User user, Room room, Date date, String text);
    List<Message> getMessageByRoom(Room room);
}
