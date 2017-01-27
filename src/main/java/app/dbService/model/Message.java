package app.dbService.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by alexa on 22.01.2017.
 */
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "room_id")
    private long roomId;
    @Column(name = "date")
    private Date date;
    @Column(name = "text")
    private String text;

    public Message(){

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Message(long userId, long roomId, Date date, String text) {
        this.userId = userId;
        this.roomId = roomId;
        this.date = date;
        this.text = text;
    }
}
