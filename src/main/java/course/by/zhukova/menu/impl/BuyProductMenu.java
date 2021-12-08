package course.by.zhukova.menu.impl;

import course.by.zhukova.entity.UserEntity;
import course.by.zhukova.menu.Menu;

import java.util.Scanner;

public class BuyProductMenu extends Menu
{
    private Scanner scanner;
    private String input;
    private UserEntity user;

    public BuyProductMenu(UserEntity user)
    {
        this.scanner = new Scanner(System.in);
        this.user = user;
    }

    @Override
    public void showMenu()
    {
        super.showMenu();
    }

    @Override
    public Menu nextMenu(UserEntity user)
    {
        return super.nextMenu(user);
    }

    @Override
    public UserEntity getUser()
    {
        return super.getUser();
    }
}
