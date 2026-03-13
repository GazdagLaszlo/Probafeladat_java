package service;

import model.Application;
import model.Menu;
import model.MenuItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MenuItemService {
    private final SessionFactory factory;

    public MenuItemService(SessionFactory factory){
        this.factory = factory;
    }

    public void createMenuItem(MenuItem menuItem){
        try(Session session = factory.openSession()){
            session.beginTransaction();
            try{
                session.persist(menuItem);
                session.getTransaction().commit();
            }catch(Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    public MenuItem getMenuItem(String id){
        try(Session session = factory.openSession()){
            return session.get(MenuItem.class, id);
        }
    }

    public void updateMenuItem(String id, MenuItem menuItem){
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            try{
                MenuItem oldMenuItem = session.get(MenuItem.class, id);
                if(oldMenuItem != null){
                    oldMenuItem.setName(menuItem.getName());
                }
                session.getTransaction().commit();
            }catch(Exception e){
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    public void deleteMenuItem(String id){
        try(Session session = factory.openSession()){
            session.beginTransaction();
            try{
                MenuItem menuItem = session.get(MenuItem.class, id);
                if(menuItem != null){
                    session.remove(menuItem);
                }
                session.getTransaction().commit();
            }catch(Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}