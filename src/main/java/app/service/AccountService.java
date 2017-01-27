package app.service;

import app.dbService.DBService;
import app.dbService.DBServiceImpl;
import app.dbService.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexa on 27.01.2017.
 */
@Service
public class AccountService {
    private DBService dbService;

    public AccountService() {
        dbService = DBServiceImpl.instance();
    }

    public List<User> getOnlineUser(int limit) {
        List<User> res = new ArrayList<>();
        res.add(new User("alex","123456","alex@mail.ru"));
        res.add(new User("John Smith","123456","wolf@mail.ru"));
        return res;
    }

    public void addNewUser(User user) {
        dbService.addUser(user);
    }
}
