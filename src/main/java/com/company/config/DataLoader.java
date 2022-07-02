package com.company.config;

import com.company.dao.auth.UserDAO;
import com.company.enums.Language;
import com.company.enums.Position;
import com.company.mapper.ApplicationContextHolder;
import com.company.models.auth.User;


/**
 * @author "Zuhriddin Shamsiddinov"
 * @created 4:32 PM on 6/20/2022 on Monday
 * @project quiz-app-maven
 */
public class DataLoader {
    static UserDAO userDAO = ApplicationContextHolder.getBean(UserDAO.class);

    static {
        if (userDAO.getUserList().size() == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(PasswordConfigurer.encode("admin"));
            admin.setEmail("admin@gmail.com");
            admin.setLanguage(Language.EN);
            admin.setPosition(Position.ADMIN);

            userDAO.save(admin);

            User teacher = new User();
            teacher.setUsername("teacher");
            teacher.setPassword(PasswordConfigurer.encode("123"));
            teacher.setEmail("teacher@gmail.com");
            teacher.setLanguage(Language.RU);
            teacher.setPosition(Position.TEACHER);

            userDAO.save(teacher);
        }
    }
}
