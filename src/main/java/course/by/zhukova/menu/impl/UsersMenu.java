package course.by.zhukova.menu.impl;

import course.by.zhukova.entity.UserEntity;
import course.by.zhukova.menu.Menu;

import java.util.Scanner;

public class UsersMenu extends Menu
{
    private Scanner scanner;
    private String input;
    private UserEntity user;

    public UsersMenu(UserEntity user)
    {
        this.scanner = new Scanner(System.in);
        this.user = user;
    }

    @Override
    public void showMenu()
    {
        System.out.println("1)Buy product");
        System.out.println("2)Show users");
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
                    showUser();
                    continue;
                }
                case "2" -> {
                    if(isAdmin())
                    {
                        updateUser();
                    }
                    continue;
                }
                case "3" -> {
                    if(isAdmin())
                    {
                        deleteUser();
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

    private void showUser()
    {

    }
    private void updateUser()
    {

    }
    private void deleteUser()
    {

    }
}
