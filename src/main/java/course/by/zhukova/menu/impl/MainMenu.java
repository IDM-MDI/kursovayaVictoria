package course.by.zhukova.menu.impl;

import course.by.zhukova.entity.UserEntity;
import course.by.zhukova.menu.Menu;

import java.util.Scanner;

public class MainMenu
{
    private Menu menu;
    private UserEntity user;

    public MainMenu()
    {
        user = new UserEntity();
    }

    public void start()
    {
        menu = new RegistrMenu(user);
        while(true)
        {
            menu = menu.nextMenu(user);
            this.user = menu.getUser();
        }
    }


}
