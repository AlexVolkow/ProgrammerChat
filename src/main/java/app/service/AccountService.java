package app.service;

import app.dbService.DBService;
import app.dbService.DBServiceImpl;
import app.dbService.model.User;
import app.service.exception.AccountAlreadyRegistered;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by alexa on 27.01.2017.
 */
@Service
public class AccountService implements UserDetailsService{
    private DBService dbService;
    private static AccountService accountService = new AccountService();
    private static final Logger logger = Logger.getLogger(AccountService.class);

    public static AccountService instance(){
        return accountService;
    }

    private static final SimpleGrantedAuthority[] ROLE_USER = {new SimpleGrantedAuthority("ROLE_USER")};

    private Map<String,User> sessionIdToProfile = new ConcurrentHashMap<>();

    private AccountService() {
        dbService = DBServiceImpl.instance();
    }

    public List<User> getOnlineUser() {
        List<User> res = new ArrayList<>();
        for (String sessionId : sessionIdToProfile.keySet()){
            res.add(sessionIdToProfile.get(sessionId));
        }
        return res;
    }

    public User getUser(long id){
        return dbService.getUserById(id);
    }

    public void addNewUser(User user) throws AccountAlreadyRegistered {
        String hash = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(hash);
        if (dbService.getUserByEmail(user.getEmail())!=null || dbService.getUserByLogin(user.getLogin())!=null){
            AccountAlreadyRegistered error = new AccountAlreadyRegistered("Account: "+user+" is already registered");
            logger.error("Account: "+user+" is already registered",error);
            throw error;
        }
        logger.info("User "+user+" registered");
        dbService.addUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = dbService.getUserByEmail(s);
        if (user==null){
            logger.error("User by email = "+s+" is not found",
                    new UsernameNotFoundException("User by email = "+s+" is not found"));
            throw new UsernameNotFoundException("User by email = "+s+" is not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(),
                true,true,true,true, Arrays.asList(ROLE_USER));
    }

    public boolean isEnter(String sessionId){
        return sessionIdToProfile.containsKey(sessionId);
    }

    public void removeSession(String sessionId){
        logger.info("User with session id = "+sessionId+" has left");
        sessionIdToProfile.remove(sessionId);
    }

    public void addSession(String sessionId,User user){
        logger.info("User "+user+" entered");
        sessionIdToProfile.put(sessionId,user);
    }

    public User getUserByLogin(String login){
        return dbService.getUserByLogin(login);
    }
    public User getUserByEmail(String email){
        return dbService.getUserByEmail(email);
    }

    public User getUserBySessionId(String sessionId){
        if (sessionIdToProfile.containsKey(sessionId)) {
            return sessionIdToProfile.get(sessionId);
        }else{
            return null;
        }
    }
}
