package com.company.menu;

import com.company.utils.BaseUtils;

import java.util.Collection;
import java.util.Collections;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @created 4:10 PM on 6/20/2022 on Monday
 * @project quiz-app-maven
 */
public class UserMenu {
    public static void userMenu() {
        BaseUtils.println("Begin Test -> 1");
        BaseUtils.println("My Result -> 2");
        BaseUtils.println("History -> 3");
        BaseUtils.println("Quit -> q");
        String choice = BaseUtils.readText("Enter choice : ");
        switch (choice) {
            case "1" -> beginTest();
            case "2" -> userResult();
            case "3" -> history();
            case "q" -> LoginMenu.quit();
        }
    }

    private static void history() {
        BaseUtils.println("history...........");
    }

    private static void userResult() {
        BaseUtils.println("result...........");
    }

    private static void beginTest() {
        BaseUtils.println("Begin test........");
    }
}
