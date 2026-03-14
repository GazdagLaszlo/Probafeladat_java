package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @ManyToOne
    private User user;
    @ManyToOne
    private Menu parentMenu;
    @OneToMany(mappedBy = "parentMenu")
    private List<Menu> subMenus;
    @OneToMany(mappedBy = "menu")
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    protected Menu() {}
    public Menu(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public void addMenuItem (MenuItem menuItem){
        this.menuItems.add(menuItem);
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public User getUser(){return user;}

    public void setName(String name){this.name = name;}
    public void setParentMenu(Menu menu) {this.parentMenu = menu;}
    public void setUser(User user) {this.user = user;}
}
