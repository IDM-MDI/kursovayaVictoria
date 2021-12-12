package course.by.zhukova.menu.impl;

import course.by.zhukova.entity.CartEntity;
import course.by.zhukova.entity.RoleEntity;
import course.by.zhukova.entity.UserEntity;
import course.by.zhukova.menu.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class SignUpMenu extends Menu
{
    private SessionFactory factory;
    private StandardServiceRegistry registry;
    private Scanner scanner;
    private String input;
    private UserEntity user;
    private List<RoleEntity> roleList;

    public SignUpMenu(UserEntity user)
    {
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.user = user;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void showMenu()
    {
    }

    @Override
    public Menu nextMenu(UserEntity user)
    {
        System.out.println("Sign up");
        System.out.println("Login:");
        user.setUserLogin(scanner.nextLine());
        System.out.println("Password:");
        user.setUserPass(scanner.nextLine());
        System.out.println("Name:");
        user.setUserName(scanner.nextLine());
        saveUser();
        addCart();
        this.user = new UserEntity();
        return new RegistrMenu(this.user);
    }
    private void saveUser()
    {
        SessionFactory factory;
        var registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = factory.openSession();
        session.beginTransaction();

        user.setUserRoleId(1);
        user.setUserCreateTime(LocalDateTime.now().toString());

        session.save(this.user);
        session.getTransaction().commit();
        session.close();


    }
    private void addCart()
    {
        Session session = factory.openSession();
        session.beginTransaction();
        CartEntity cart = new CartEntity();
        cart.setCartUserId(this.user.getIduser());
        session.getTransaction().commit();
        session.close();
    }

    private RoleEntity getUserRole()
    {
        for (int i = 0; i < roleList.size(); i++)
        {
            if(roleList.get(i).getIdrole().equals(1))
            {
                return roleList.get(i);
            }
        }
        return null;
    }
    @Override
    public UserEntity getUser()
    {
        return user;
    }
}
