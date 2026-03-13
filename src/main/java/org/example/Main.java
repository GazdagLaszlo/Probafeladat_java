package org.example;

import model.Application;
import model.Menu;
import model.MenuItem;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import service.ApplicationService;
import service.MenuItemService;
import service.MenuService;
import service.UserService;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Application.class)
                .addAnnotatedClass(model.Menu.class)
                .addAnnotatedClass(model.MenuItem.class)
                .addAnnotatedClass(model.Wallpaper.class)
                .addAnnotatedClass(model.Theme.class)
                .buildSessionFactory();

        UserService userService = new UserService(factory);
        MenuService menuService = new MenuService(factory);
        MenuItemService menuItemService = new MenuItemService(factory);
        ApplicationService applicationService = new ApplicationService(factory);

        User user = new User();
        user.setName("Gazdag László");
        userService.createUser(user);

        Application app1 = new Application();
        app1.setName("Wieldy");
        Application app2 = new Application();
        app2.setName("AXAMOP");
        applicationService.createApplication(app1);
        applicationService.createApplication(app2);


        Menu menu = new Menu();
        menu.setUser(user);
        menu.setName("Alkalmazásaim");
        menuService.createMenu(menu);


        //A feladat leírásából nem jött át nekem egyértelműen,
        // hogy a MenuItem-en keresztül kell-e hozzáadnom a kedvenc alkalmazásokat.
        //Ha nem, akkor kell Application lista a Menu-be és oda kell hozzáadni
        // a kedvenc alkalmazásokat
        /*
        MenuItem menuItem1 = new MenuItem();
        menuItem1.setName("Munkaerő gazdálkodás");
        menuItem1.setMenu(menu);
        menuItem1.setApplication(app1);
        menuItemService.createMenuItem(menuItem1);

        MenuItem menuItem2 = new MenuItem();
        menuItem2.setName("Üzemeltetés");
        menuItem2.setMenu(menu);
        menuItem2.setApplication(app2);
        menuItemService.createMenuItem(menuItem2);
        */

        menuService.addFavouriteApplication(menu.getId(), app1.getId());
        menuService.addFavouriteApplication(menu.getId(), app2.getId());

        factory.close();
    }
}