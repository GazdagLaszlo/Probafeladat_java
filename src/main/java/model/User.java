package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @ManyToOne
    private Wallpaper wallpaper;
    @ManyToOne
    private Theme theme;
    @OneToMany(mappedBy = "user")
    private List<Menu> menus = new ArrayList<Menu>();

    protected User(){}
    public User(String name){
        this.name = name;
    }

    public String getId() {return id;};
    public String getName() {return name;}
    public Wallpaper getWallpaper() {return wallpaper;}
    public Theme getTheme() {return theme;}
    public List<Menu> getMenus() {return menus;}

    public void setName(String name){this.name = name;}
    public void setWallpaper(Wallpaper wallpaper) {this.wallpaper = wallpaper;}
    public void setTheme(Theme theme) {this.theme = theme;}
}
