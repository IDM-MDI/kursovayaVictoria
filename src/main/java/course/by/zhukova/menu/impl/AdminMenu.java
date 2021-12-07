package course.by.zhukova.menu.impl;

import course.by.zhukova.entity.UserEntity;
import course.by.zhukova.menu.Menu;

import java.util.Scanner;

public class AdminMenu extends Menu
{
    private Scanner scanner;
    private String input;
    private UserEntity user;

    public AdminMenu(UserEntity user)
    {
        this.scanner = new Scanner(System.in);
        this.user = user;
    }

    @Override
    public void showMenu()
    {
        System.out.println("1)Products" +
                "\n2)Users" +
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
                    return new UsersMenu(this.user);
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
