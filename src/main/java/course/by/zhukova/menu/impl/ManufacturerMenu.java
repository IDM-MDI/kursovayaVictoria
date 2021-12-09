package course.by.zhukova.menu.impl;

import course.by.zhukova.entity.*;
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

public class ManufacturerMenu extends Menu
{
    private SessionFactory factory;
    private StandardServiceRegistry registry;
    private Scanner scanner;
    private String input;
    private UserEntity user;
    private List<CountryEntity> countries;
    private List<ManufacturerEntity> manufactures;
    private final String PHONE_NUMBER_REGEX = "\\+\\d{3}\\s\\d{2}\\s\\d{3}\\s\\d{2}";
    private final String EMAIL_REGEX = "[a-zA-Z0-9]+@(gmail|mail|yandex)\\.(com|ru|org)";
    private final String NUMBER = "\\d+";

    public ManufacturerMenu(UserEntity user)
    {
        this.registry = new StandardServiceRegistryBuilder().configure().build();
        this.factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        this.manufactures = new ArrayList<>();
        this.countries = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.user = user;
    }

    @Override
    public void showMenu()
    {
        System.out.println("1)Show manufactures"
                + "\n2)Add manufacture"
                + "\n3)Update manufacture"
                + "\n4)Delete manufacture"
                + "\n9)Back" +
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
                    showManufacturer();
                    continue;
                }
                case "2" -> {
                    addManufacturer();
                    continue;
                }
                case "3" -> {
                    updateManufacturer();
                    continue;
                }
                case "4" -> {
                    deleteManufacturer();
                    continue;
                }
                case "9" -> {
                    return new AdminMenu(this.user);
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

    @Override
    public UserEntity getUser()
    {
        return this.user;
    }

    private void addManufacturer()
    {
        Session session = factory.openSession();
        ManufacturerEntity manuf = createManufacturer();
        if(manuf == null)
        {
            return;
        }
        else
        {
            session.beginTransaction();
            session.save(manuf);
            session.getTransaction().commit();
            session.close();
        }
    }

    private ManufacturerEntity createManufacturer()
    {
        ManufacturerEntity entity = new ManufacturerEntity();

        System.out.println("Enter manufacture name: ");
        input = scanner.nextLine();
        entity.setManufacturerName(input);

        System.out.println("Enter manufacture phone number: ");
        input = scanner.nextLine();
        if(input.matches(PHONE_NUMBER_REGEX))
        {
            entity.setPhoneNumber(input);
        }
        else
        {
            System.out.println("Error input number, it should be(N - number):\n+NNN NN NNN NN");
            return null;
        }

        System.out.println("Enter manufacture mail: ");
        input = scanner.nextLine();
        if(input.matches(EMAIL_REGEX))
        {
            entity.setManufacturerMail(input);
        }
        else
        {
            System.out.println("Error mail input, it should be like:\nexample@gmail.com");
            return null;
        }
        showCountries();
        System.out.println("Enter country id:");
        input = scanner.nextLine();
        if(input.matches(NUMBER) && isCountryExist(Integer.parseInt(input)))
        {
            entity.setManufacturerCountry(Integer.parseInt(input));
        }
        else
        {
            System.out.println("Wrong input number");
            return null;
        }
        return entity;
    }

    public void showManufacturer()         //TODO: make joins
    {
        Session session = factory.openSession();

        session.beginTransaction();
        List l = session.createQuery("select mf.idmanufacturer, mf.manufacturerName, mf.phoneNumber,mf.manufacturerMail,c.countryName " +
                "from ManufacturerEntity mf left join CountryEntity c ON mf.manufacturerCountry = c.id").getResultList();

        Iterator iterator = l.iterator();
        while(iterator.hasNext())
        {
            Object rows[] = (Object[])iterator.next();
            System.out.println(rows[0]+ "\t--\t" +rows[1] + "\t--\t"+rows[2]+"\t--\t"+rows[3]+"\t--\t" +rows[4]);
        }
        session.getTransaction().commit();
        session.close();
    }
    private void showCountries()
    {
        for (CountryEntity i:countries)
        {
            System.out.println(i.getIdcountry() + ":\t" + i.getCountryName());
        }
    }
    private CountryEntity getCountry(Integer id)
    {
        for (CountryEntity i:countries)
        {
            if(i.getIdcountry().equals(id))
            {
                return i;
            }
        }
        return null;
    }
    private void updateManufacturer()           //TODO: show manuf
    {
        showManufacturer();
        Session session = factory.openSession();

        session.beginTransaction();

        System.out.println("Enter which id need to update:");
        input = scanner.nextLine();
        if(input.matches(NUMBER))
        {
            Integer id = Integer.parseInt(input);
            isManufacturerExist(id);
            ManufacturerEntity entity = createManufacturer();
            if(entity == null)
            {
                return;
            }
            entity.setIdmanufacturer(id);

            session.update(entity);
            session.getTransaction().commit();
            session.close();
        }
        else
        {
            session.close();
            System.out.println("Wrong input");
            return;
        }

    }
    private void deleteManufacturer()           //READY
    {
        showManufacturer();
        Session session = factory.openSession();

        session.beginTransaction();

        System.out.println("Enter which id need to delete:");
        input = scanner.nextLine();
        if(input.matches(NUMBER))
        {
            Integer id = Integer.parseInt(input);
            isManufacturerExist(id);
            ManufacturerEntity entity = new ManufacturerEntity();
            entity.setIdmanufacturer(id);

            session.delete(entity);
            session.getTransaction().commit();
            session.close();
        }
        else
        {
            session.close();
            System.out.println("Wrong input");
            return;
        }
    }

    private void getAllInfo()                   //READY
    {
        Session session = factory.openSession();

        session.beginTransaction();

        countries = session.createQuery("from CountryEntity ",CountryEntity.class).getResultList();
        manufactures = session.createQuery("from ManufacturerEntity ",ManufacturerEntity.class).getResultList();

        session.getTransaction().commit();
        session.close();
    }
    private boolean isManufacturerExist(Integer id)
    {
        boolean result = false;
        for (ManufacturerEntity i: manufactures)
        {
            if(i.getIdmanufacturer().equals(id))
            {
                result = true;
            }
        }
        return result;
    }
    private boolean isCountryExist(Integer id)
    {
        boolean result = false;
        for (CountryEntity i: countries)
        {
            if(i.getIdcountry().equals(id))
            {
                result = true;
            }
        }
        return result;
    }
}
