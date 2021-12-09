package course.by.zhukova.menu.impl;

import course.by.zhukova.entity.CartEntity;
import course.by.zhukova.entity.OrderEntity;
import course.by.zhukova.entity.ProductEntity;
import course.by.zhukova.entity.UserEntity;
import course.by.zhukova.menu.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UsersMenu extends Menu
{
    private SessionFactory factory;
    private StandardServiceRegistry registry;
    private Scanner scanner;
    private String input;
    private UserEntity user;
    private List<ProductEntity> productEntityList;
    private List<OrderEntity> orderEntities;
    private List<CartEntity> cartEntities;
    private ProductMenu menu;

    public UsersMenu(UserEntity user)
    {
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.menu  = new ProductMenu(this.user);
        this.productEntityList = new ArrayList<>();
        this.orderEntities = new ArrayList<>();
        this.cartEntities = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.user = user;
    }

    @Override
    public void showMenu()
    {
        System.out.println("1)Buy product");
        System.out.println("2)Show your cart");
        System.out.println("3)Show your account info");
        System.out.println("9)Back" +
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
                    return new BuyProductMenu(this.user);
                }
                case "2" -> {
                    showCart();
                    continue;
                }
                case "3" -> {
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

    private void showCart()
    {
        showAccInfo();
        getAllInfo();
        System.out.println("Products: { ");
        List<ProductEntity> list = findProducts(findOrderID(findCartID()));
        for (ProductEntity i: list)
        {
            System.out.print(i.getProductName() + ", ");
        }
        System.out.print(" }");
    }

    @Override
    public UserEntity getUser()
    {
        return user;
    }

    private boolean isAdmin()
    {
        return this.user.getUserRoleId().equals(2);
    }

    private void showAccInfo()
    {
        System.out.println("Login: " + this.user.getUserLogin()
                + "\nPassword: " + this.user.getUserPass()
                + "\nName: " + this.user.getUserName()
                + "\nRegistration time: " + this.user.getUserCreateTime()
                + "\nRole: Admin");
    }
    private void getAllInfo()
    {
        Session session = factory.openSession();

        session.beginTransaction();

        cartEntities = session.createQuery("from CartEntity ",CartEntity.class).getResultList();
        orderEntities = session.createQuery("from OrderEntity ",OrderEntity.class).getResultList();
        productEntityList = menu.getProductList();
        session.getTransaction().commit();
        session.close();
    }
    private List<Integer> findCartID()
    {
        List<Integer> result = new ArrayList<>();

        for(CartEntity i : cartEntities)
        {
            if(i.getCartUserId().equals(this.user.getIduser()))
            {
                result.add(i.getIdcart());
            }
        }

        return result;
    }
    private List<Integer> findOrderID(List<Integer> integers)
    {
        List<Integer> result = new ArrayList<>();

        for (OrderEntity orderEntity : orderEntities)
        {
            for (int j = 0; j < integers.size(); j++)
            {
                if (orderEntity.getOrderCartId().equals(integers.get(j)))
                {
                    result.add(orderEntity.getOrderProductId());
                }
            }
        }

        return result;
    }
    private List<ProductEntity> findProducts(List<Integer> integers)
    {
        List<ProductEntity> result = new ArrayList<>();

        for (int i = 0; i < productEntityList.size(); i++)
        {
            for (int j = 0; j < integers.size(); j++)
            {
                if(productEntityList.get(i).getIdProduct().equals(integers.get(j)))
                {
                    result.add(productEntityList.get(i));
                }
            }
        }

        return result;
    }
}
