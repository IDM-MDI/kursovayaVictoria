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
import java.util.Iterator;
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
    private List<MaterialEntity> materialEntities;
    private List<MaterialtypeEntity> materialtypeEntities;
    private List<SeasonEntity> seasonEntities;
    private final String NUMBER = "\\d+";

    public ProductMenu(UserEntity user)
    {
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.seasonEntities = new ArrayList<>();
        this.materialtypeEntities = new ArrayList<>();
        this.materialEntities = new ArrayList<>();
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
                            "\n4)Delete product" +
                            "\n5)Add material");
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
                        System.out.println("Products empty");
                        continue;
                    }
                    showProducts();
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
                case "5" -> {
                    if(isAdmin())
                    {
                        addMaterials();
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
                    continue;
                }
            }
            return null;
        }
    }

    public void showProducts()
    {
        Session session = factory.openSession();

        session.beginTransaction();
        List l = session.createQuery("select p.id,p.productName,mf.manufacturerName,cl.colorName,s.seasonName,mi.materialtypeName,mo.materialtypeName,mu.materialtypeName,ms.materialtypeName " +
                "from ProductEntity p " +
                "left join ManufacturerEntity mf on p.productManufacturerId = mf.id " +
                "left join ColorEntity cl on p.productColorId = cl.id " +
                "left join MaterialEntity m on p.productMaterialId = m.id " +
                "left join SeasonEntity s on m.materialSeason = s.id " +
                "left join MaterialtypeEntity mi on m.materialInside = mi.id " +
                "left join MaterialtypeEntity mo on m.materialOutside = mo.id " +
                "left join MaterialtypeEntity ms on m.materialSole = ms.id " +
                "left join MaterialtypeEntity mu on m.materialUpper = mu.id ").getResultList();

        String formats = "%s\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s";
        String formats1 = "%s\t\t\t%s\t\t%s\t\t\t%s\t\t%s\t\t%s\t\t%s\t\t%s";
        System.out.println(String.format(formats,"ProductID","ProductName","Manufacture","Color","Season","MaterialInside","MaterialOutside","MaterialUpper","MaterialSole"));
        Iterator iterator = l.iterator();
        while(iterator.hasNext())
        {
            Object rows[] = (Object[])iterator.next();
            System.out.println(String.format(formats1,rows[0],rows[1],rows[2],rows[3],rows[4],rows[5],rows[6],rows[7]));
        }
        session.getTransaction().commit();
        session.close();
    }
    public void showMaterials()
    {
        Session session = factory.openSession();

        session.beginTransaction();
        List l = session.createQuery("select m.id, mi.materialtypeName, mo.materialtypeName, ms.materialtypeName,mu.materialtypeName " +
                "from MaterialEntity m " +
                "left join MaterialtypeEntity mi on m.materialInside = mi.id " +
                "left join MaterialtypeEntity mo on m.materialOutside = mo.id " +
                "left join MaterialtypeEntity ms on m.materialSole = ms.id " +
                "left join MaterialtypeEntity mu on m.materialUpper = mu.id ").getResultList();

        String formats = "%s\t%s\t\t%s\t\t%s";
        String formats1 = "%s\t\t\t%s\t%s\t%s\t%s";
        System.out.println(String.format(formats,"MaterialID","MaterialInside","MaterialOutside","MaterialSole","MaterialUpper"));
        Iterator iterator = l.iterator();
        while(iterator.hasNext())
        {
            Object rows[] = (Object[])iterator.next();
            System.out.println(String.format(formats1,rows[0],rows[1],rows[2],rows[3],rows[4]));
        }
        session.getTransaction().commit();
        session.close();
    }

    private void addProduct()
    {
        Session session = factory.openSession();
        ProductEntity product = createProduct();
        if(product == null)
        {
            return;
        }
        else
        {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void addMaterials()
    {
        Session session = factory.openSession();

        session.beginTransaction();

        MaterialEntity entity = new MaterialEntity();
        System.out.println("MaterialInside: ");
        input = choiceMaterialType(materialtypeEntities);
        if(input.matches(NUMBER) && isMaterialTypeExist(Integer.parseInt(input)))
        {
            entity.setMaterialInside(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Error input number");
            return;
        }
        System.out.println("MaterialOutside: ");
        input = choiceMaterialType(materialtypeEntities);
        if(input.matches(NUMBER) && isMaterialTypeExist(Integer.parseInt(input)))
        {
            entity.setMaterialOutside(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Error input number");
            return;
        }
        System.out.println("MaterialSole: ");
        input = choiceMaterialType(materialtypeEntities);
        if(input.matches(NUMBER) && isMaterialTypeExist(Integer.parseInt(input)))
        {
            entity.setMaterialSole(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Error input number");
            return;
        }
        System.out.println("MaterialUpper: ");
        input = choiceMaterialType(materialtypeEntities);
        if(input.matches(NUMBER) && isMaterialTypeExist(Integer.parseInt(input)))
        {
            entity.setMaterialUpper(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Error input number");
            return;
        }
        System.out.println("Season: ");
        input = choiceSeason(seasonEntities);
        if(input.matches(NUMBER) && isSeasonExist(Integer.parseInt(input)))
        {
            entity.setMaterialSeason(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Error input number");
            return;
        }

        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    private void updateProduct()
    {
        showProducts();
        input = scanner.nextLine();
        if(input.matches(NUMBER))
        {
            Session session = factory.openSession();

            session.beginTransaction();
            Integer id = Integer.parseInt(input);
            isProductExist(id);
            ProductEntity entity = createProduct();
            entity.setIdProduct(id);
            session.update(entity);
            session.getTransaction().commit();
            session.close();
        }
        else
        {
            System.out.println("Error while input number");
            return;
        }



    }
    private void deleteProduct()
    {
        showProducts();
        input = scanner.nextLine();
        if(input.matches(NUMBER))
        {
            Session session = factory.openSession();

            session.beginTransaction();
            Integer id = Integer.parseInt(input);
            isProductExist(id);
            ProductEntity entity = new ProductEntity();
            entity.setIdProduct(id);
            session.delete(entity);
            session.getTransaction().commit();
            session.close();
        }
    }
    private ProductEntity createProduct()
    {
        ProductEntity entity = new ProductEntity();
        System.out.println("Enter product name: ");
        input = scanner.nextLine();
        entity.setProductName(input);

        input = choiceColor(colors);
        if(input.matches(NUMBER) && isColorExist(Integer.parseInt(input)))
        {
            entity.setProductColorId(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Input char is not number");
            return null;
        }

        System.out.println("Enter the product size:");
        input = scanner.nextLine();
        if(input.matches(NUMBER))
        {
            entity.setProductSize(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Input char is not number");
            return null;
        }

        input = choiceMaterial(materialEntities);
        if(input.matches(NUMBER) && isMaterialExist(Integer.parseInt(input)))
        {
            entity.setProductMaterialId(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Input char is not number");
            return null;
        }
        input = choiceManuf(manufacturers);
        if(input.matches(NUMBER) && isManufactExist(Integer.parseInt(input)))
        {
            entity.setProductManufacturerId(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Input char is not number");
            return null;
        }

        return entity;
    }
    private void getAllInfo()
    {
        Session session = factory.openSession();

        session.beginTransaction();

        products = session.createQuery("from ProductEntity",ProductEntity.class).getResultList();
        seasonEntities = session.createQuery("from SeasonEntity ",SeasonEntity.class).getResultList();
        colors = session.createQuery("from ColorEntity",ColorEntity.class).getResultList();
        manufacturers = session.createQuery("from ManufacturerEntity ",ManufacturerEntity.class).getResultList();
        materialEntities = session.createQuery("from MaterialEntity ",MaterialEntity.class).getResultList();
        materialtypeEntities = session.createQuery("from MaterialtypeEntity ",MaterialtypeEntity.class).getResultList();

        session.getTransaction().commit();
        session.close();
    }
    @Override
    public UserEntity getUser()
    {
        return user;
    }
    public List<ProductEntity> getProductList()
    {
        Session session = factory.openSession();
        session.beginTransaction();
        products = session.createQuery("from ProductEntity",ProductEntity.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return products;
    }
    private boolean isAdmin()
    {
        return this.user.getUserRoleId().equals(2);
    }
    private String choiceColor(List<ColorEntity> colors)        //TODO: Make choice
    {
        for(ColorEntity i:colors)
        {
            System.out.println(i.getIdcolor() + " :\t" + i.getColorName());
        }
        System.out.println("Choice color to product: ");
        return scanner.nextLine();
    }
    private String choiceSeason(List<SeasonEntity> list)        //TODO: Make choice
    {
        for(SeasonEntity i:list)
        {
            System.out.println(i.getIdseason() + " :\t" + i.getSeasonName());
        }
        System.out.println("Choice season to material: ");
        return scanner.nextLine();
    }
    private String choiceMaterialType(List<MaterialtypeEntity> type)        //TODO: Make choice
    {
        for(MaterialtypeEntity i:type)
        {
            System.out.println(i.getIdmaterialtype() + " :\t" + i.getMaterialtypeName());
        }
        System.out.println("Choice materialtype to material: ");
        return scanner.nextLine();
    }
    private String choiceManuf(List<ManufacturerEntity> manufacturers)  //TODO: Make choice
    {
        ManufacturerMenu menu = new ManufacturerMenu(this.user);
        menu.showManufacturer();
        System.out.println("Choice manufacturer to product: ");
        return scanner.nextLine();
    }
    private String choiceMaterial(List<MaterialEntity> materialEntities)             //TODO: Make choice
    {
        showMaterials();
        System.out.println("Choice material to product: ");
        return scanner.nextLine();
    }
    public boolean isProductExist(Integer id)
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
    private boolean isMaterialTypeExist(Integer id)
    {
        boolean result = false;
        for (MaterialtypeEntity i: materialtypeEntities)
        {
            if(i.getIdmaterialtype().equals(id))
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
    private boolean isMaterialExist(Integer id)
    {
        boolean result = false;
        for (MaterialEntity i: materialEntities)
        {
            if(i.getIdmaterial().equals(id))
            {
                result = true;
            }
        }
        return result;
    }
    private boolean isSeasonExist(Integer id)
    {
        boolean result = false;
        for (SeasonEntity i: seasonEntities)
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
        return colors.isEmpty() && manufacturers.isEmpty() && materialEntities.isEmpty();
    }

}
