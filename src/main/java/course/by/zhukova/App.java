package course.by.zhukova;

import course.by.zhukova.entity.*;
import course.by.zhukova.menu.Menu;
import course.by.zhukova.menu.impl.MainMenu;
import course.by.zhukova.menu.impl.ManufacturerMenu;
import course.by.zhukova.menu.impl.RegistrMenu;
import course.by.zhukova.menu.impl.SignInMenu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main(final String[] args) throws Exception
    {
        SessionFactory factory;
        var registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = factory.openSession();

        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCountryName("China");
        countryEntity.setIdcountry(1);
        ManufacturerEntity manufacturer = new ManufacturerEntity();
        manufacturer.setManufacturerName("newManufacturer");
        manufacturer.setPhoneNumber("+999 88 777 66");
        manufacturer.setManufacturerMail("email@gmail.com");
        manufacturer.setManufacturerCountry(1);

        session.beginTransaction();
        session.save(manufacturer);
        session.getTransaction().commit();

        session.close();

//        ManufacturerMenu menu = new ManufacturerMenu(new UserEntity());
//        menu.nextMenu(new UserEntity());
//        MainMenu menu = new MainMenu();
//        menu.start();
    }
    
}
