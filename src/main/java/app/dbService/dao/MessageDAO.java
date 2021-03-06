package app.dbService.dao;

import app.dbService.model.Message;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by alexa on 22.01.2017.
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class MessageDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public MessageDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() { // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void insert(Message message){
        currentSession().save(message);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Message message){
        currentSession().delete(message);
    }

    public Message get(long id){
        return currentSession().get(Message.class,id);
    }

    public List<Message> getMessages(){
        List<Object> list = currentSession().createQuery("FROM Message").list();
        List<Message> res = new ArrayList<>();
        for (Object obj : list){
            res.add((Message) obj);
        }
        return res;
    }
    public void dropTable(){
        currentSession().clear();
    }
}
