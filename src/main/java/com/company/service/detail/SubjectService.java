package com.company.service.detail;

import com.company.dao.detail.SubjectDAO;
import com.company.mapper.ApplicationContextHolder;
import com.company.models.detail.Subject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @created 4:05 PM on 6/20/2022 on Monday
 * @project quiz-app-maven
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectService {
    private static SubjectService instance;
    private final SubjectDAO subjectDAO = ApplicationContextHolder.getBean(SubjectDAO.class);

    public void create(Subject subject) {
        subjectDAO.save(subject);
    }

    public void deleteByName(String name) {
        subjectDAO.deleteByName(name);
    }

    public void update(Subject subject, String username) {
        subjectDAO.update(subject, username);
    }

    public void subjectList() {
        subjectDAO.findAll();
    }

    public void persist() {
        subjectDAO.persist();
    }

    public static SubjectService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SubjectService();
        }
        return instance;
    }
}
