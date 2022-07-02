package com.company.menu;

import com.company.config.PasswordConfigurer;
import com.company.dto.auth.UserCreateDTO;
import com.company.dto.auth.UserLoginDTO;
import com.company.mapper.ApplicationContextHolder;
import com.company.service.auth.UserService;
import com.company.utils.BaseUtils;
import com.company.utils.Colors;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @created 4:13 PM on 6/20/2022 on Monday
 * @project quiz-app-maven
 */
public class LoginMenu {
    private final static UserService USER_SERVICE = ApplicationContextHolder.getBean(UserService.class);


    public static void loginMenu() {
        BaseUtils.println("Login -> 1");
        BaseUtils.println("Register -> 2");
        BaseUtils.println("Quit -> q");
        BaseUtils.println("?:");

        String choice = BaseUtils.readText("Enter choice : ");
        switch (choice) {
            case "1" -> login();
            case "2" -> register();
            case "q" -> quit();
        }
    }

    public static void quit() {
        BaseUtils.println("Quit.....", Colors.RED);
        USER_SERVICE.persist();
        System.exit(0);
    }

    private static void register() {
        String username = BaseUtils.readText("username : ");
        String password = BaseUtils.readText("password : ");
        String email = BaseUtils.readText("email : ");
        String language = BaseUtils.readText("language : ");

        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setUsername(username);
        userCreateDTO.setPassword(PasswordConfigurer.encode(password));
        userCreateDTO.setEmail(email);
        userCreateDTO.setLanguage(language);
        USER_SERVICE.create(userCreateDTO);
        System.out.println("Successfully registered");
    }

    private static void login() {
        String username = BaseUtils.readText("username : ");
        String password = BaseUtils.readText("password : ");
        UserLoginDTO loginDTO = new UserLoginDTO(username, password);
        String response = USER_SERVICE.login(loginDTO);
        System.out.println(response);
    }


}
