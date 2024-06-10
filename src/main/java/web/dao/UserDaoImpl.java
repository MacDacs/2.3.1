package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public boolean updateUser(User user, int id) {
        try {
            User update = em.find(User.class, id);
            update.setName(user.getName());
            update.setEmail(user.getEmail());
            em.merge(update);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void deleteUser(int id) {
        try {
            em.remove(em.find(User.class, id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUser(int id) {
        User user = null;
        try {
            user = em.find(User.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
