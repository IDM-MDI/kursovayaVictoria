package course.by.zhukova;

import course.by.zhukova.entity.ColorEntity;
import course.by.zhukova.menu.Menu;
import course.by.zhukova.menu.impl.MainMenu;
import course.by.zhukova.menu.impl.RegistrMenu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.Console;
import java.util.Scanner;

public class App 
{
    public static void main(final String[] args) throws Exception
    {
//        SessionFactory factory;
//        var registry = new StandardServiceRegistryBuilder().configure().build();
//        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        System.out.println("Hibernate tutorial");
//
//        Session session = factory.openSession();
//
//        session.beginTransaction();
//
//        session.save();
//        session.getTransaction().commit();
//
//        session.close();

        MainMenu menu = new MainMenu();
        menu.start();
    }
    
}
