package com.company.service.auth;

import com.company.config.PasswordConfigurer;
import com.company.dao.auth.UserDAO;
import com.company.dao.detail.SubjectDAO;
import com.company.dto.auth.UserCreateDTO;
import com.company.dto.auth.UserLoginDTO;
import com.company.dto.auth.UserUpdateDTO;
import com.company.enums.Language;
import com.company.enums.Position;
import com.company.mapper.ApplicationContextHolder;
import com.company.models.auth.User;
import com.company.models.detail.Subject;
import com.company.ui.UI;
import com.company.utils.BaseUtils;
import com.company.utils.Colors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static UserService instance;
    private final UserDAO userDAO = ApplicationContextHolder.getBean(UserDAO.class);
    private final SubjectDAO subjectDAO = ApplicationContextHolder.getBean(SubjectDAO.class);

    public void create(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(PasswordConfigurer.encode(userCreateDTO.getPassword()));
        user.setEmail(userCreateDTO.getEmail());
        user.setLanguage(Language.findByName(userCreateDTO.getLanguage()));
        user.setPosition(Position.USER);
        userDAO.save(user);
    }

    public void update(UserUpdateDTO userUpdateDTO, String username) {
        User user = new User();
        user.setUsername(userUpdateDTO.getUsername());
        user.setEmail(userUpdateDTO.getEmail());
        user.setLanguage(Language.findByName(userUpdateDTO.getLanguage()));
        user.setPosition(Position.findByName(userUpdateDTO.getPosition()));
        userDAO.update(user, username);
    }

    public void delete(String username) {
        userDAO.deleteByUserName(username);
        BaseUtils.println("Successfully deleted", Colors.GREEN);
    }

    public void userList() {
        List<User> userList = userDAO.findAll();
        for (User user : userList) {
            if (Objects.equals(UI.session.getId(), user.getId())) {
                continue;
            }
            BaseUtils.println("%s :  %s  |  %s  |  %s".formatted(user.getUsername(), user.getEmail(), user.getLanguage(), user.getPosition()));
        }
    }

    public String login(UserLoginDTO loginDTO) {
        User user = userDAO.findByUserName(loginDTO.getUsername());
        if (Objects.isNull(user)) {
            return "User not found by username '%s'".formatted(loginDTO.getUsername());
        }
        if (!PasswordConfigurer.matchPassword(loginDTO.getPassword(),user.getPassword())) {
            return "Bad credentials";
        }
        UI.isSigned = true;
        UI.session = user;
        return "Logged in successfully";
    }

    public void persist() {
        userDAO.persist();
    }


    public void setRole(String username) {
        User byUserName = userDAO.findByUserName(username);
        if (Objects.isNull(byUserName)) {
            BaseUtils.println("Not found user", Colors.RED);
            return;
        }
        String roleName = BaseUtils.readText("Enter new role  : ");
        Position position = Position.findByName(roleName);
        byUserName.setPosition(position);
        BaseUtils.println("Successfully set role", Colors.GREEN);
    }

    public void teacherList() {
        List<User> teachers = userDAO.teacherList();
        for (User teacher : teachers) {
            Optional<Subject> subjectOptional =
                    subjectDAO.findByTeacherId(teacher.getId());
            if (subjectOptional.isEmpty()) {
                BaseUtils.println("Teacher have not subjects", Colors.RED);
                return;
            }
            BaseUtils.println("%s . %s  ->  %s".formatted(teacher.getUsername(), teacher.getEmail(), subjectOptional.get().getName()));
        }
    }



    public static UserService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UserService();
        }
        return instance;
    }
}
