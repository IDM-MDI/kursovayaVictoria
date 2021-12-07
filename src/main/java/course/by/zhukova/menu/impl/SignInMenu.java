package course.by.zhukova.menu.impl;

import course.by.zhukova.entity.RoleEntity;
import course.by.zhukova.entity.UserEntity;
import course.by.zhukova.menu.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SignInMenu extends Menu
{
    private Scanner scanner;
    private String input;
    private String login,password;
    private UserEntity user;
    private List<UserEntity> list;

    public SignInMenu(UserEntity user)
    {
        this.user = user;
        scanner = new Scanner(System.in);
        list = new ArrayList<>();
    }

    @Override
    public void showMenu() {}

    @Override
    public Menu nextMenu(UserEntity user)
    {
        System.out.println("Sign in");
        System.out.println("Login:");
        login = scanner.nextLine();
        System.out.println("Password:");
        password = scanner.nextLine();
        getUserFrom();

        if(isUser())
        {
            if(this.user.getUserRoleId().equals(2))
            {
                return new AdminMenu(this.user);
            }
            return new UserMenu(this.user);
        }
        else
        {
            return new RegistrMenu(this.user);
        }
    }

    private void getUserFrom()
    {
        SessionFactory factory;
        var registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = factory.openSession();

        session.beginTransaction();

        Query<UserEntity> one = session.createQuery("from UserEntity",UserEntity.class);
        list = one.getResultList();

        session.getTransaction().commit();
        session.close();
    }
    private boolean isUser()
    {
        for(UserEntity i:list)
        {
            if(login.equals(i.getUserLogin()))
            {
                if(password.equals(i.getUserPass()))
                {
                    this.user = i;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public UserEntity getUser()
    {
        return user;
    }

}
