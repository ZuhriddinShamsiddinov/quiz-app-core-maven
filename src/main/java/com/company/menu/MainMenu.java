package com.company.menu;

import com.company.enums.Position;
import com.company.ui.UI;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @created 5:00 PM on 6/20/2022 on Monday
 * @project quiz-app-maven
 */
public class MainMenu {


    public static void menu() throws RuntimeException {
        if (!UI.isSigned) {
            LoginMenu.loginMenu();
        } else if (UI.session.getPosition().equals(Position.USER)) {
            UserMenu.userMenu();
        } else if (UI.isSigned && UI.session.getPosition().equals(Position.TEACHER)) {
            TeacherMenu.teacherMenu();
        } else if (UI.isSigned && UI.session.getPosition().equals(Position.ADMIN)) {
            AdminMenu.adminMenu();
        }
        menu();
    }

}
