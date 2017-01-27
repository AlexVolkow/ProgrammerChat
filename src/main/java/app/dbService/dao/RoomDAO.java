package app.dbService.dao;

import app.dbService.model.Room;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alexa on 22.01.2017.
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class RoomDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public RoomDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() { // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void insert(Room room){
        currentSession().save(room);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)

    public void delete(Room room){
        currentSession().delete(room);
    }

    public Room get(long id){
        return currentSession().get(Room.class,id);
    }

    public Room getByName(String name){
        Criteria criteria = currentSession().createCriteria(Room.class);
        return (Room) criteria.add(Restrictions.eq("name",name)).uniqueResult();
    }

    public List<Room> getAll(){
        return (List<Room>) currentSession().createQuery("from Room as r order by r.date").list();
    }
}
