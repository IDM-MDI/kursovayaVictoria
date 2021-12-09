package course.by.zhukova.menu.impl;

import course.by.zhukova.entity.UserEntity;
import course.by.zhukova.menu.Menu;

import java.util.Scanner;

public class RegistrMenu extends Menu
{
    private Scanner scanner;
    private UserEntity user;
    private  String input;

    public RegistrMenu(UserEntity user)
    {
        this.user = user;
        scanner = new Scanner(System.in);
    }

    @Override
    public void showMenu()
    {
        System.out.println("1)Sign in\n2)Sign up\n0)Exit");
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
                    return new SignInMenu(this.user);
                }
                case "2" -> {
                    return new SignUpMenu(this.user);
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
    public void exit()
    {
        super.exit();
    }
    @Override
    public UserEntity getUser()
    {
        return user;
    }

}
