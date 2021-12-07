package course.by.zhukova.menu.impl;

import course.by.zhukova.entity.UserEntity;
import course.by.zhukova.menu.Menu;

import java.util.Scanner;

public class ProductMenu extends Menu
{
    private Scanner scanner;
    private String input;
    private UserEntity user;

    public ProductMenu(UserEntity user)
    {
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
            showMenu();
            input = scanner.nextLine();
            switch(input)
            {
                case "1" -> {
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
                        updateProduct();
                    }
                    continue;
                }
                case "4" -> {
                    if(isAdmin())
                    {
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

    }
    private void showProduct()
    {

    }
    private void updateProduct()
    {

    }
    private void deleteProduct()
    {

    }

}
