package app.chat.message;

import app.dbService.model.Message;
import app.dbService.model.User;

/**
 * Created by alexa on 02.02.2017.
 */
public class DirectMessage extends ChatMessage {
    private String to;

    @Override
    public String toString() {
        return "DirectMessage{" +"from='"+getLogin()+"' "+
                "to='" + to + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DirectMessage)) return false;
        if (!super.equals(o)) return false;

        DirectMessage that = (DirectMessage) o;

        return to != null ? to.equals(that.to) : that.to == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }

    public String getTo() {

        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom(){
        return getLogin();
    }

    public DirectMessage(Message message, String from, String to, StatusMessage statusMessage) {
        super(message, from, statusMessage);
        this.to = to;
    }
}
