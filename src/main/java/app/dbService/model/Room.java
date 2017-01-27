package app.dbService.model;

import javax.persistence.*;

/**
 * Created by alexa on 22.01.2017.
 */
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "owner_id")
    private long ownerId;

    public Room(){

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public Room(long id, String name, long ownerId) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
    }
}
