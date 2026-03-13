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

    public String getId() {return id;}
    public String getName() {return name;}
    public void setName(String name){this.name = name;}
}
