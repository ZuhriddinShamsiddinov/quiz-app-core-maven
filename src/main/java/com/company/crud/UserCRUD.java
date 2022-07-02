package com.company.crud;

import com.company.dao.auth.UserDAO;
import com.company.dto.auth.UserCreateDTO;
import com.company.dto.auth.UserUpdateDTO;
import com.company.mapper.ApplicationContextHolder;
import com.company.menu.AdminMenu;
import com.company.models.auth.User;
import com.company.service.auth.UserService;
import com.company.utils.BaseUtils;
import com.company.utils.Colors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author 'Zuhriddin Shamsiddinov' 10:52 PM - 6/20/2022 on Mon
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCRUD {
    private static UserCRUD instance;
    private final static UserService USER_SERVICE = ApplicationContextHolder.getBean(UserService.class);
    private final static UserDAO USER_DAO = ApplicationContextHolder.getBean(UserDAO.class);

    public void create() {
        String username = BaseUtils.readText("Enter username : ");
        String password = BaseUtils.readText("Enter  password : ");
        String email = BaseUtils.readText("Enter  email : ");
        String language = BaseUtils.readText("Enter  language : ");
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setUsername(username);
        userCreateDTO.setPassword(password);
        userCreateDTO.setEmail(email);
        userCreateDTO.setLanguage(language);
        User byUserName = USER_DAO.findByUserName(username);
        if (!Objects.isNull(byUserName)) {
            BaseUtils.println("username already taken '%s'".formatted(username));
        } else {
            USER_SERVICE.create(userCreateDTO);
            BaseUtils.println("Successfully created", Colors.GREEN);
        }
    }

    public void delete() {
        String username = BaseUtils.readText("Enter username for delete : ");
        USER_SERVICE.delete(username);
    }

    public void update() {
        userList();
        String username = BaseUtils.readText("Enter id of user for update : ");
        User byId = USER_DAO.findByUserName(username);
        if (Objects.isNull(byId)) {
            BaseUtils.println("Not found user by username '%s'".formatted(username), Colors.RED);
        } else {
            UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
            userUpdateDTO.setUsername(BaseUtils.readText("Enter username : "));
            userUpdateDTO.setEmail(BaseUtils.readText("Enter email : "));
            userUpdateDTO.setLanguage(BaseUtils.readText("Enter language : "));
            userUpdateDTO.setPosition(BaseUtils.readText("Enter position : "));
            USER_SERVICE.update(userUpdateDTO, username);
        }
    }

    public void userList() {
        USER_SERVICE.userList();
    }

    public void logout() {
        USER_SERVICE.persist();
        BaseUtils.println("Successfully logged out");
        AdminMenu.adminMenu();
    }

    public static UserCRUD getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UserCRUD();
        }
        return instance;
    }

    public void setRole() {
        userList();
        String username = BaseUtils.readText("Enter username for set role : ");
        USER_SERVICE.setRole(username);
    }

    public void teacherList() {
        USER_SERVICE.teacherList();
    }

    public void revokeRole() {
        USER_SERVICE.revokeRole();
    }
}
