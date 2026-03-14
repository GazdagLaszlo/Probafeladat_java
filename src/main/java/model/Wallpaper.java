package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Wallpaper {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @OneToMany(mappedBy = "wallpaper")
    private List<User> users = new ArrayList<User>();

    protected Wallpaper(){}
    public Wallpaper(String name){
        this.name = name;
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public List<User> getUsers() {return users;}

    public void setName(String name){this.name = name;}
}
