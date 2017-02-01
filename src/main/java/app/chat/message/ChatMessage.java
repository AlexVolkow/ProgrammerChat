package app.chat.message;

import app.dbService.model.Message;


/**
 * Created by alexa on 31.01.2017.
 */
public class ChatMessage extends Message {
    private String login;

    private StatusMessage status;

    public ChatMessage(Message message, String login, StatusMessage statusMessage){
        super(message.getUserId(),message.getDate(),message.getText());
        this.login = login;
        this.status = statusMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChatMessage)) return false;

        ChatMessage that = (ChatMessage) o;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "login='" + login + '\'' +
                ", status=" + status +
                '}';
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
}
