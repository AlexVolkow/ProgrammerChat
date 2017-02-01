package app.chat.message;

import app.dbService.model.Message;

import java.util.Date;

/**
 * Created by alexa on 01.02.2017.
 */
public class InfoMessage extends ChatMessage {
    public InfoMessage(Date date, String text) {
        super(new Message(-1,date,text),"*", StatusMessage.INFO);
    }
}
