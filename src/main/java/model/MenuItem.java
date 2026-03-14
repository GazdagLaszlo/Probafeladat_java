package model;

import jakarta.persistence.*;

@Entity
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @ManyToOne
    private Menu menu;
    @ManyToOne
    private Application application;

    protected MenuItem() {}
    public MenuItem(String name, Menu menu, Application application){
        this.name = name;
        this.menu = menu;
        this.application = application;
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public Menu getMenu() { return menu; }
    public Application getApplication() { return application; }

    public void setName(String name){this.name = name;}
    public void setMenu(Menu menu){ this.menu = menu;}
    public void setApplication(Application application){ this.application = application;}
}
