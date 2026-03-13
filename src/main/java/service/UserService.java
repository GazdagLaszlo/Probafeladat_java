package service;


import model.Application;
import model.Menu;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserService {
    private final SessionFactory factory;

    public UserService(SessionFactory factory){
        this.factory = factory;
    }

    public void createUser(User user){
        try(Session session = factory.openSession()){
            session.beginTransaction();
            try {
                session.persist(user);
                session.getTransaction().commit();
            } catch (Exception e){
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    public User getUser(String id){
        try(Session session = factory.openSession()){
            return session.get(User.class, id);
        }
    }

    public void updateUser(String id, User user){
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            try{
                User oldUser = session.get(User.class, id);
                if(oldUser != null){
                    oldUser.setName(user.getName());
                }
                session.getTransaction().commit();
            }catch (Exception e){
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    public void deleteUser(String id){
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            try{
                User user = session.get(User.class, id);
                if(user != null){
                    session.remove(user);
                }
                session.getTransaction().commit();
            }catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}