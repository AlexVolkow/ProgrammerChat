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

    public Room(){}

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

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (ownerId != room.ownerId) return false;
        return name != null ? name.equals(room.name) : room.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (ownerId ^ (ownerId >>> 32));
        return result;
    }

    public Room(String name, long ownerId) {
        this.name = name;
        this.ownerId = ownerId;
    }
}
