package service;

import model.User;
import model.Wallpaper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class WallpaperService {
    private final SessionFactory factory;

    public WallpaperService(SessionFactory factory){
        this.factory = factory;
    }

    public void addWallpaper(Wallpaper wallpaper){
        try(Session session = factory.openSession()){
            session.beginTransaction();
            try{
                session.persist(wallpaper);
                session.getTransaction().commit();
            }catch(Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    public void selectWallpaper(String userId, String wallpaperId){
        try(Session session = factory.openSession()){
            session.beginTransaction();
            try {
                User user = session.get(User.class, userId);
                Wallpaper wallpaper = session.get(Wallpaper.class, wallpaperId);
                if(user != null && wallpaper != null){
                    user.setWallpaper(wallpaper);
                }
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}