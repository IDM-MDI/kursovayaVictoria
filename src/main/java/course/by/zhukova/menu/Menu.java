package course.by.zhukova.menu;

import course.by.zhukova.entity.UserEntity;

public abstract class Menu
{
    public void showMenu(){};
    public Menu nextMenu(UserEntity user)
    {
        return null;
    }
    public UserEntity getUser(){return null;}

    public void exit()
    {
        System.exit(0);
    }
}
