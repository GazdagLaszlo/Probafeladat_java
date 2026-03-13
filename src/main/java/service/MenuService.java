package service;


import model.Application;
import model.Menu;
import model.MenuItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MenuService {
    private final SessionFactory factory;

    public MenuService(SessionFactory factory){
        this.factory = factory;
    }

    public void createMenu(Menu menu){
        try(Session session = factory.openSession()){
            session.beginTransaction();
            try {
                session.persist(menu);
                session.getTransaction().commit();
            } catch (Exception e){
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    public Menu getMenu(String id){
        try(Session session = factory.openSession()){
            return session.get(Menu.class, id);
        }
    }

    public void deleteMenu(String id){
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            try{
                Menu menu = session.get(Menu.class, id);
                if(menu != null){
                    session.remove(menu);
                }
                session.getTransaction().commit();
            }catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
    public void addSubMenu(String parentMenuId, String subMenuId) {
        try(Session session= factory.openSession()) {
            session.beginTransaction();
            try {
                Menu parentMenu= session.get(Menu.class, parentMenuId);
                Menu subMenu = session.get(Menu.class, subMenuId);
                if (parentMenu != null && subMenu != null) {
                    subMenu.setParentMenu(parentMenu);
                }
                session.getTransaction().commit();
            } catch (Exception e){
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    public void addFavouriteApplication(String menuId, String applicationId) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            try{
                //A feladat leírásából nem jött át nekem egyértelműen,
                // hogy a MenuItem-en keresztül kell-e hozzáadnom a kedvenc alkalmazásokat.
                //Ha nem, akkor kell Application lista a Menu-be és oda kell hozzáadni
                // a kedvenc alkalmazásokat
                Menu menu = session.get(Menu.class, menuId);
                Application application = session.get(Application.class, applicationId);
                if(menu != null && application != null) {
                    MenuItem menuItem = new MenuItem();


                    menuItem.setMenu(menu);
                    menuItem.setApplication(application);
                    session.persist(menuItem);
                }
                session.getTransaction().commit();
            } catch(Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }
}