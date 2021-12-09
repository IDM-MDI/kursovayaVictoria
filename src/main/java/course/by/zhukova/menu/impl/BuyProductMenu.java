package course.by.zhukova.menu.impl;

import course.by.zhukova.entity.*;
import course.by.zhukova.menu.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuyProductMenu extends Menu
{
    private SessionFactory factory;
    private StandardServiceRegistry registry;
    private Scanner scanner;
    private String input;
    private UserEntity user;
    private ProductMenu menu;
    private final String NUMBER = "\\d+";
    private List<ProductEntity> productEntityList;
    private List<CartEntity> cartEntities;

    public BuyProductMenu(UserEntity user)
    {
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.productEntityList = new ArrayList<>();
        this.cartEntities = new ArrayList<>();
        this.menu  = new ProductMenu(this.user);
        this.scanner = new Scanner(System.in);
        this.user = user;
    }

    @Override
    public void showMenu()
    {
        menu.showProducts();
        System.out.println("Choice which shoes u should buy");
        System.out.println("0)Back");
    }

    @Override
    public Menu nextMenu(UserEntity user)
    {
        while(true)
        {
            getAllInfo();
            showMenu();
            input = scanner.nextLine();
            switch(input)
            {
                case "0" -> {
                    return new UsersMenu(this.user);
                }
                default -> {
                    if(input.matches(NUMBER) && menu.isProductExist(Integer.parseInt(input)))
                    {
                        addOrder();
                        return new UsersMenu(this.user);
                    }
                    else
                    {
                        System.out.println("Try again");
                        continue;
                    }
                }
            }

        }
    }

    private void addOrder()
    {
        Session session = factory.openSession();
        session.beginTransaction();
        OrdersEntity order = new OrdersEntity();
        CartEntity cart = findCart(user.getIduser(),cartEntities);
        order.setOrderCartId(cart.getCartUserId());
        order.setOrderProductId(Integer.parseInt(input));
        session.save(order);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public UserEntity getUser()
    {
        return this.user;
    }

    private void getAllInfo()
    {
        Session session = factory.openSession();

        session.beginTransaction();

        productEntityList = menu.getProductList();
        cartEntities = session.createQuery("from CartEntity ",CartEntity.class).getResultList();

        session.getTransaction().commit();
        session.close();
    }
    private CartEntity findCart(Integer id, List<CartEntity> carts)
    {
        boolean result = false;
        for (CartEntity i : carts)
        {
            if(i.getCartUserId().equals(id))
            {
                return i;
            }
        }
        return null;
    }
}
