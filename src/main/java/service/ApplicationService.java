package service;

import model.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ApplicationService {
    private final SessionFactory factory;

    public ApplicationService(SessionFactory factory){
        this.factory = factory;
    }

    public void createApplication(Application application){
        try(Session session = factory.openSession()){
            session.beginTransaction();
            try{
                session.persist(application);
                session.getTransaction().commit();
            }catch (Exception e) {
                session.getTransaction().rollback();
                throw e;
            }
        }
    }

    public void launchApplication(String applicationId){
        try(Session session = factory.openSession()){
            Application application = session.get(Application.class, applicationId);
            if(application != null){
                System.out.println("Launching app");
            }
        }
    }
}
