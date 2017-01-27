package app.dbService;

import app.dbService.model.User;
import org.junit.Test;

/**
 * Created by alexa on 22.01.2017.
 */
public class DBServiceTest {
    @Test
    public void addUser() throws Exception {
        DBService dbService = DBServiceImpl.instance();
        User admin = new User("admin","admin","admin@admin");
        long id = dbService.addUser(admin);
        admin.setId(id);
        System.out.println(admin);
        dbService.deleteUser(admin);
    }

}