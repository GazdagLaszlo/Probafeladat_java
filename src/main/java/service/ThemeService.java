package service;

import model.Theme;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ThemeService {
    private final SessionFactory factory;

    public ThemeService(SessionFactory factory){
        this.factory = factory;
    }

    public void selectTheme(String userId, String themeId){
        try(Session session = factory.openSession()){
            session.beginTransaction();
            try{
                User user= session.get(User.class, userId);
                Theme theme = session.get(Theme.class, themeId);
                if(user != null && theme != null){
                    user.setTheme(theme);
                }
                session.getTransaction().commit();
            } catch(Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}