package com.company.dao.detail;

import com.company.dao.base.BaseDAO;
import com.company.models.auth.User;
import com.company.models.detail.Subject;
import com.company.utils.BaseUtils;
import com.company.utils.Colors;
import com.google.gson.reflect.TypeToken;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @author "Zuhriddin Shamsiddinov"
 * @created 4:04 PM on 6/20/2022 on Monday
 * @project quiz-app-maven
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectDAO extends BaseDAO {

    private static SubjectDAO subjectDAO;
    private final List<Subject> subjectList = load();

    public void save(Subject subject) {
        subject.setId(System.currentTimeMillis());
        subjectList.add(subject);
    }

    public void update(Subject newSubject, String username) {
        Subject subjectOptional = findBySubjectName(username);
        if (Objects.isNull(subjectOptional)) {
            BaseUtils.println("Subject not found", Colors.RED);
        } else {
            subjectOptional.setName(newSubject.getName());
            subjectOptional.setDescription(newSubject.getDescription());
            BaseUtils.println("Successfully updated", Colors.GREEN);
        }
    }

    public void deleteByName(String username) {
        Subject subjectOptional = findBySubjectName(username);
        if (Objects.isNull(subjectOptional)) {
            BaseUtils.println("Subject not found", Colors.RED);
        } else {
            subjectList.remove(subjectOptional);
            BaseUtils.println("Successfully deleted", Colors.GREEN);
        }
    }

    public Optional<Subject> findById(Long id) {
        for (Subject subject : subjectList) {
            if (Objects.equals(subject.getId(), id)) {
                return Optional.of(subject);
            }
        }
        return Optional.empty();
    }

    public Subject findBySubjectName(String name) {
        return subjectList.stream()
                .filter(subject -> subject.getName()
                        .equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public Optional<Subject> findByTeacherId(Long id) {
        for (Subject subject : subjectList) {
            if (subject.getTeacherId() == id) {
                return Optional.of(subject);
            }
        }
        return Optional.empty();
    }

    public void findAll() {
        subjectList
                .forEach
                        (subject -> BaseUtils.println
                                ("%s :  %s   ->  %s"
                                        .formatted
                                                (subject.getId(),
                                                        subject.getName(),
                                                        subject.getDescription()), Colors.PURPLE));
    }

    private List<Subject> load() {
        try (InputStream is = new FileInputStream("src/main/resources/db/subject.json")) {
            String jsonSTRING = new String(is.readAllBytes());
            return gson.fromJson(jsonSTRING, new TypeToken<List<Subject>>() {
            }.getType());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void persist() {
        try (OutputStream is = new FileOutputStream("src/main/resources/db/subject.json")) {
            String jsonSTRING = gson.toJson(subjectList);
            is.write(jsonSTRING.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static SubjectDAO getInstance() {
        if (Objects.isNull(subjectDAO)) {
            subjectDAO = new SubjectDAO();
        }
        return subjectDAO;
    }

}
