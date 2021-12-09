package course.by.zhukova.menu.impl;

import course.by.zhukova.entity.UserEntity;
import course.by.zhukova.menu.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AdminMenu extends Menu
{
    private SessionFactory factory;
    private StandardServiceRegistry registry;
    private Scanner scanner;
    private String input;
    private UserEntity user;

    public AdminMenu(UserEntity user)
    {
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.scanner = new Scanner(System.in);
        this.user = user;
    }

    @Override
    public void showMenu()
    {
        System.out.println("1)Products" +
                "\n2)Show all users" +
                "\n3)Manufacturer" +
                "\n4)Show all info about this account" +
                "\n9)Sign out" +
                "\n0)Exit");
    }

    @Override
    public Menu nextMenu(UserEntity user)
    {
        while(true)
        {
            showMenu();
            input = scanner.nextLine();
            switch(input)
            {
                case "1" -> {
                    return new ProductMenu(this.user);
                }
                case "2" -> {
                    showAccounts();
                    continue;
                }
                case "3" -> {
                    return new ManufacturerMenu(this.user);
                }
                case "4" -> {
                    showAccInfo();
                    continue;
                }
                case "9" -> {
                    return new RegistrMenu(new UserEntity());
                }
                case "0" -> {
                    System.out.println("See you next time");
                    exit();
                }
                default -> {
                    System.out.println("Try again");
                    showMenu();
                }
            }
            return null;
        }
    }

    private void showAccounts()
    {
        Session session = factory.openSession();

        session.beginTransaction();
        List l = session.createQuery("SELECT u.id,u.userName,u.userLogin,u.userPass,r.roleName,u.userCreateTime " +
                "FROM UserEntity u " +
                "LEFT JOIN RoleEntity r on u.userRoleId = r.id ").getResultList();

        String formats = "%s\t%s\t\t%s\t\t%s\t\t%s\t\t%s";
        String formats1 = "%s\t\t\t%s\t%s\t%s\t%s\t%s";
        System.out.println(String.format(formats,"UserID","UserName","UserLogin","UserPassword","RoleName","UserCreatedTime"));
        Iterator iterator = l.iterator();
        while(iterator.hasNext())
        {
            Object rows[] = (Object[])iterator.next();
            System.out.println(String.format(formats1,rows[0],rows[1],rows[2],rows[3],rows[4],rows[5]));
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public UserEntity getUser()
    {
        return user;
    }

    private void showAccInfo()
    {
        System.out.println("Login: " + this.user.getUserLogin()
                + "\nPassword: " + this.user.getUserPass()
                + "\nName: " + this.user.getUserName()
                + "\nRegistration time: " + this.user.getUserCreateTime()
                + "\nRole: Admin");
    }
}
