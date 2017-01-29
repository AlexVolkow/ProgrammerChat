package app.service;

import app.dbService.DBService;
import app.dbService.DBServiceImpl;
import app.dbService.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;


import java.util.*;

/**
 * Created by alexa on 27.01.2017.
 */
@Service
public class AccountService implements UserDetailsService{
    private DBService dbService;

    private static final SimpleGrantedAuthority[] ROLE_USER = {new SimpleGrantedAuthority("ROLE_USER")};

    private Map<String,User> sessionIdToProfile = new HashMap<>();

    public AccountService() {
        dbService = DBServiceImpl.instance();
    }

    public List<User> getOnlineUser(int limit) {
        List<User> res = new ArrayList<>();
        for (String sessionId : sessionIdToProfile.keySet()){
            res.add(sessionIdToProfile.get(sessionId));
        }
        return res;
    }

    public void addNewUser(User user) {
        String hash = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(hash);
        dbService.addUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = dbService.getUserByEmail(s);
        if (user==null){
            throw new UsernameNotFoundException("User by email = "+s+" is not found");
        }
        sessionIdToProfile.put(RequestContextHolder.currentRequestAttributes().getSessionId(),user);
        return new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(),
                true,true,true,true, Arrays.asList(ROLE_USER));
    }

    public boolean isEnter(String sessionId){
        return sessionIdToProfile.containsKey(sessionId);
    }

    public void removeSession(String sessionId){
        sessionIdToProfile.remove(sessionId);
    }

    public User getUserByLogin(String login){
        return dbService.getUserByLogin(login);
    }
    public User getUserByEmail(String email){
        return dbService.getUserByEmail(email);
    }
}
