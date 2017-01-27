package app.dbService.dao;

import app.dbService.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alexa on 22.01.2017.
 */
@Repository
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class UserDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() { // Извлекает текущий
        return sessionFactory.getCurrentSession(); // сеанс из фабрики
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void insert(User user) {
        currentSession().save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(User user) {
        currentSession().update(user);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(User user){currentSession().delete(user);}

    public User get(long id) {
        return currentSession().get(User.class,id);
    }

    public User getByLogin(String login) {
        Criteria criteria = currentSession().createCriteria(User.class);
        return (User) criteria.add(Restrictions.eq("login",login)).uniqueResult();
    }

    public User getByEmail(String email) {
        Criteria criteria = currentSession().createCriteria(User.class);
        return (User) criteria.add(Restrictions.eq("email",email)).uniqueResult();
    }
}
