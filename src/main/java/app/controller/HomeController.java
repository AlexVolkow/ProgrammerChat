package app.controller;

import app.dbService.model.Room;
import app.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * Created by alexa on 26.01.2017.
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private RoomService roomService;

    @RequestMapping(method = RequestMethod.GET)
    public String showHomePage(Model model){
        Map<Room,Integer> rooms = roomService.getRooms();
        model.addAttribute("rooms",rooms);
        return "home";
    }
}
