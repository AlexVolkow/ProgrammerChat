package app.dbService.dao;

import app.dbService.DBService;
import app.dbService.DBServiceImpl;
import app.dbService.model.Room;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alexa on 30.01.2017.
 */
public class RoomDAOTest {
    @Test
    public void insert() throws Exception {
        DBService dbService = DBServiceImpl.instance();
        Room room = new Room("programming",23);
        dbService.addRoom(room);
        Room res = dbService.getRoomByName("programming");
        System.out.println(res);
    }

}