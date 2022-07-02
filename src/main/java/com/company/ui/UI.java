package com.company.ui;

import com.company.config.DataLoader;
import com.company.menu.MainMenu;
import com.company.models.auth.User;


public class UI {
    private static final DataLoader dataLoader = new DataLoader();
    public static boolean isSigned = false;
    public static User session = new User();

    public static void main(String[] args) {
        MainMenu.menu();
    }

}
