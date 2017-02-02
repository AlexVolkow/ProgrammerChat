package app.service;

import app.chat.message.DirectMessage;
import app.chat.message.StatusMessage;
import app.chat.message.ChatMessage;
import app.dbService.model.User;

/**
 * Created by alexa on 02.02.2017.
 */
public class Filter {
    public static boolean apply(User user, ChatMessage message){
        if (message.getStatus()== StatusMessage.INFO){
            String[] words = message.getText().split(" ");
            if (words.length==2){
                if (words[0].equals(user.getLogin()) && words[1].equals("joined")){
                    return false;
                }
            }
        }
        if (message.getStatus()==StatusMessage.DIRECT){
            if (message instanceof DirectMessage){
                DirectMessage direct = (DirectMessage) message;
                if (!(direct.getFrom().equals(user.getLogin()) || direct.getTo().equals(user.getLogin()))){
                    return false;
                }
                if (direct.getFrom().equals(user.getLogin())){

                }
            }
        }
        return true;
    }
}
