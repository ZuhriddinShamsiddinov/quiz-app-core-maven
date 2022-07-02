package com.company.dao.auth;

import com.company.dao.base.BaseDAO;
import com.company.enums.Position;
import com.company.models.auth.User;
import com.company.utils.BaseUtils;
import com.company.utils.Colors;
import com.google.gson.reflect.TypeToken;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserDAO extends BaseDAO {

    public List<User> userList = load();
    private static UserDAO instance;


    public void save(User user) {
        user.setId(System.currentTimeMillis());
        userList.add(user);
    }


    public void update(User user, String username) {
        User subjectOptional = findByUserName(username);
        if (Objects.isNull(subjectOptional)) {
            BaseUtils.println("User not found", Colors.RED);
        } else {
            subjectOptional.setUsername(user.getUsername());
            subjectOptional.setPassword(user.getPassword());
            subjectOptional.setEmail(user.getEmail());
            subjectOptional.setLanguage(user.getLanguage());
            BaseUtils.println("Successfully updated", Colors.GREEN);
        }
    }

    public void deleteByUserName(String username) {
        User userOptional = findByUserName(username);
        if (Objects.isNull(userOptional)) {
            BaseUtils.println("User not found", Colors.RED);
        } else {
            userList.remove(userOptional);
            BaseUtils.println("Successfully deleted", Colors.GREEN);
        }
    }

    public List<User> findAll() {
        return userList;
    }

    public User findByUserName(String userName) {
        for (User user : this.userList) {
            if (userName.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }


    private List<User> load() {
        try (InputStream is = new FileInputStream("src/main/resources/db/user.json")) {
            String jsonSTRING = new String(is.readAllBytes());
            return gson.fromJson(jsonSTRING, new TypeToken<List<User>>() {
            }.getType());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void persist() {
        try (OutputStream is = new FileOutputStream("src/main/resources/db/user.json")) {
            String jsonSTRING = gson.toJson(userList);
            is.write(jsonSTRING.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static UserDAO getInstance() {
        if (Objects.isNull(instance)) {
            instance = new UserDAO();
        }
        return instance;
    }


    public List<User> teacherList() {
        return userList.stream()
                .filter(user -> user.getPosition().equals(Position.TEACHER))
                .collect(Collectors.toList());
    }


}
