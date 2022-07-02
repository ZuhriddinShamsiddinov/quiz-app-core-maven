package com.company.menu;

import com.company.utils.BaseUtils;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @created 4:10 PM on 6/20/2022 on Monday
 * @project quiz-app-maven
 */
public class TeacherMenu {
    public static void teacherMenu() {
        BaseUtils.println("Test -> 1");
        BaseUtils.println("Students -> 2");
        BaseUtils.println("My subject -> 3");
        BaseUtils.println("Quit -> q");
        String choice = BaseUtils.readText("Enter choice : ");
        switch (choice) {
            case "1" -> testCRUD();
            case "2" -> studentCRUD();
            case "3" -> teacherOwnSubject();
            case "q" -> LoginMenu.quit();
        }
    }

    private static void teacherOwnSubject() {
        BaseUtils.println("teacher subject........");
    }

    private static void studentCRUD() {
        BaseUtils.println("student crud.....");
    }

    private static void testCRUD() {
        BaseUtils.println("test crud.....");
    }
}
