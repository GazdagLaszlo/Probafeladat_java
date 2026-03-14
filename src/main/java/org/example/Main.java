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

import java.util.List;

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
        ApplicationService applicationService = new ApplicationService(factory);

        User me = createMe(userService);
        List<Application> apps = createApplications(applicationService);
        createMenu(menuService, me, apps);

        //A feladat leírásából nem jött át nekem egyértelműen,
        // hogy a MenuItem-en keresztül kell-e hozzáadnom a kedvenc alkalmazásokat.
        //Ha nem, akkor kell Application lista a Menu-be és oda kell hozzáadni
        // a kedvenc alkalmazásokat

        factory.close();
    }

    private static User createMe(UserService userService){
        User user = new User("Gazdag László");
        userService.createUser(user);
        return user;
    }

    private static List<Application> createApplications(ApplicationService appService){
        Application app1 = new Application("Wieldy");
        Application app2 = new Application("AXAMOP");
        appService.createApplication(app1);
        appService.createApplication(app2);

        return List.of(app1, app2);
    }

    private static void createMenu(MenuService menuService, User user, List<Application> apps) {
        Menu menu = new Menu("Alkalmazásaim", user);
        menuService.createMenu(menu);

        for (Application app : apps) {
            menuService.addFavouriteApplication(menu.getId(), app.getId());
        }
    }
}