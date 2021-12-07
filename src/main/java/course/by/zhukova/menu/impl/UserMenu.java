package course.by.zhukova.menu.impl;

import course.by.zhukova.entity.UserEntity;
import course.by.zhukova.menu.Menu;

import java.util.Scanner;

public class UserMenu extends Menu
{
    private Scanner scanner;
    private UserEntity user;

    @Override
    public UserEntity getUser()
    {
        return user;
    }

    private String input;

    public UserMenu(UserEntity user)
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
}
