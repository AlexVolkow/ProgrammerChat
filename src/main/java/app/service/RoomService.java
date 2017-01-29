package app.service;

import app.dbService.DBService;
import app.dbService.DBServiceImpl;
import app.dbService.model.Room;
import app.dbService.model.User;
import app.service.exception.RoomAlreadyCreate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexa on 30.01.2017.
 */
@Service
public class RoomService {
    private Map<Room,Integer> rooms = new HashMap<>();

    private DBService dbService = DBServiceImpl.instance();

    public RoomService() {
        List<Room> roomList = dbService.allRooms();
        for (Room room: roomList){
            rooms.put(room,0);
        }
    }

    public void createRoom(String name, User owner) throws RoomAlreadyCreate{
        Room room = new Room(name,owner.getId());
        if (dbService.getRoomByName(name)!=null){
            throw new RoomAlreadyCreate("Room: "+room+" is already create");
        }
        long id = dbService.addRoom(room);
        room.setId(id);
        rooms.put(room,0);
    }

    public Map<Room, Integer> getRooms() {
        return rooms;
    }

    public Room getRoomByName(String name){
        return dbService.getRoomByName(name);

    }
}
