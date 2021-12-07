package course.by.zhukova.menu.impl;

import course.by.zhukova.entity.UserEntity;
import course.by.zhukova.menu.Menu;

import java.util.Scanner;

public class ManufacturerMenu extends Menu
{
    private Scanner scanner;
    private String input;
    private UserEntity user;

    public ManufacturerMenu(UserEntity user)
    {
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
                    showMenu();
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

    }
    private void showManufacturer()
    {

    }
    private void updateManufacturer()
    {

    }
    private void deleteManufacturer()
    {

    }
}
