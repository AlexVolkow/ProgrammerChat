package app.chat;

import java.util.Date;

/**
 * Created by alexa on 31.01.2017.
 */
public class SimpleMessage {
    private String text;

    private String login;

    private StatusMessage status;

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SimpleMessage{" +
                "text='" + text + '\'' +
                ", login='" + login + '\'' +
                ", status=" + status +
                ", date=" + date +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public StatusMessage getStatus() {
        return status;
    }

    public void setStatus(StatusMessage status) {
        this.status = status;
    }

    public SimpleMessage() {

    }

    public SimpleMessage(String login, Date date, String text,StatusMessage statusMessage) {
        this.text = text;
        this.login = login;
        this.status = statusMessage;
        this.date = date;
    }
}
