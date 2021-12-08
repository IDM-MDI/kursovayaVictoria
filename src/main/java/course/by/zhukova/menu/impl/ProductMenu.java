package course.by.zhukova.menu.impl;

import course.by.zhukova.entity.*;
import course.by.zhukova.menu.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductMenu extends Menu
{
    private SessionFactory factory;
    private StandardServiceRegistry registry;
    private Scanner scanner;
    private String input;
    private UserEntity user;
    private List<ProductEntity> products;
    private List<ManufacturerEntity> manufacturers;
    private List<ColorEntity> colors;
    private List<SeasonEntity> seasons;

    public ProductMenu(UserEntity user)
    {
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.seasons = new ArrayList<>();
        this.products = new ArrayList<>();
        this.manufacturers = new ArrayList<>();
        this.colors = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.user = user;
    }

    @Override
    public void showMenu()
    {
        System.out.println("1)Show products");
        if(isAdmin())
        {
            System.out.println("2)Add product" +
                            "\n3)Update product" +
                            "\n4)Delete product");
        }
        System.out.println("9)Back" +
                "\n0)Exit");
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
                case "1" -> {
                    if(isListsEmpty())
                    {
                        continue;
                    }
                    showProduct();
                    continue;
                }
                case "2" -> {
                    if(isAdmin())
                    {
                        addProduct();
                    }
                    continue;
                }
                case "3" -> {
                    if(isAdmin())
                    {
                        if(products.isEmpty())
                        {
                            continue;
                        }
                        updateProduct();
                    }
                    continue;
                }
                case "4" -> {
                    if(isAdmin())
                    {
                        if(products.isEmpty())
                        {
                            continue;
                        }
                        deleteProduct();
                    }
                    continue;
                }
                case "9" -> {
                    if(isAdmin())
                    {
                        return new AdminMenu(this.user);
                    }
                    else
                    {
                        return new UserMenu(this.user);
                    }
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

    @Override
    public UserEntity getUser()
    {
        return user;
    }
    private boolean isAdmin()
    {
        return this.user.getUserRoleId().equals(2);
    }

    private void addProduct()
    {
        Session session = factory.openSession();

        session.beginTransaction();
        session.save(createProduct());
        session.getTransaction().commit();
        session.close();
    }
    private void getAllInfo()
    {
        Session session = factory.openSession();

        session.beginTransaction();

        products = session.createQuery("from ProductEntity",ProductEntity.class).getResultList();
        colors = session.createQuery("from ColorEntity",ColorEntity.class).getResultList();
        manufacturers = session.createQuery("from ManufacturerEntity ",ManufacturerEntity.class).getResultList();
        seasons = session.createQuery("from SeasonEntity ",SeasonEntity.class).getResultList();

        session.getTransaction().commit();
        session.close();
    }
    private void showProduct()                                  //TODO: Make joins
    {
        String formats = "%d\t\t%s\t%d";
        System.out.println();
        for(ProductEntity i: products)
        {
            System.out.println(String.format(formats,i));
        }
    }
    private void updateProduct()
    {
        Session session = factory.openSession();

        session.beginTransaction();

        Integer id = Integer.parseInt(scanner.nextLine());
        isProductExist(id);
        ProductEntity entity = createProduct();
        entity.setIdProduct(id);

        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }
    private void deleteProduct()
    {
        Session session = factory.openSession();
        session.beginTransaction();

        Integer id = Integer.parseInt(scanner.nextLine());
        isProductExist(id);
        ProductEntity entity = new ProductEntity();
        entity.setIdProduct(id);

        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }
    private ProductEntity createProduct()
    {
        ProductEntity entity = new ProductEntity();

        entity.setProductColorId(Integer.parseInt(scanner.nextLine()));

        entity.setProductSize(Integer.parseInt(scanner.nextLine()));
        entity.setProductSeasonId(Integer.parseInt(scanner.nextLine()));
        entity.setProductManufacturerId(Integer.parseInt(scanner.nextLine()));

        return entity;
    }

    private String choiceColor(List<ColorEntity> colors)        //TODO: Make choice
    {
        for(ColorEntity i:colors)
        {
            System.out.println(i.getIdcolor() + " : " + i.getColorName());
        }
        return scanner.nextLine();
    }
    private String choiceManuf(List<ManufacturerEntity> manufacturers)  //TODO: Make choice
    {
        for(ManufacturerEntity i:manufacturers)
        {
            System.out.println(i.getIdmanufacturer() + " : " + i.getManufacturerName());
        }
        return scanner.nextLine();
    }
    private String choiceSeason(List<SeasonEntity> seasons)             //TODO: Make choice
    {
        for(SeasonEntity i:seasons)
        {
            System.out.println(i.getIdseason() + " : " + i.getSeasonName());
        }
        return scanner.nextLine();
    }
    private boolean isProductExist(Integer id)
    {
        boolean result = false;
        for (ProductEntity i: products)
        {
            if(i.getIdProduct().equals(id))
            {
                result = true;
            }
        }
        return result;
    }
    private boolean isColorExist(Integer id)
    {
        boolean result = false;
        for (ColorEntity i: colors)
        {
            if(i.getIdcolor().equals(id))
            {
                result = true;
            }
        }
        return result;
    }
    private boolean isManufactExist(Integer id)
    {
        boolean result = false;
        for (ManufacturerEntity i: manufacturers)
        {
            if(i.getIdmanufacturer().equals(id))
            {
                result = true;
            }
        }
        return result;
    }
    private boolean isSeasonExist(Integer id)
    {
        boolean result = false;
        for (SeasonEntity i: seasons)
        {
            if(i.getIdseason().equals(id))
            {
                result = true;
            }
        }
        return result;
    }

    private boolean isListsEmpty()
    {
        return colors.isEmpty() && manufacturers.isEmpty() && seasons.isEmpty();
    }

}
