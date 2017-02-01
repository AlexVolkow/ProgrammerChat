package app.service;

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
        return true;
    }
}
