package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    protected Application(){}
    public Application(String name){
        this.name = name;
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public void setName(String name){this.name = name;}
}
