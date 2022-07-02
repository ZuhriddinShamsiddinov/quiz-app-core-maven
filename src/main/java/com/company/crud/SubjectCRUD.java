package com.company.crud;

import com.company.dao.auth.UserDAO;
import com.company.dao.detail.SubjectDAO;
import com.company.mapper.ApplicationContextHolder;
import com.company.menu.AdminMenu;
import com.company.models.auth.User;
import com.company.models.detail.Subject;
import com.company.service.auth.UserService;
import com.company.service.detail.SubjectService;
import com.company.utils.BaseUtils;
import com.company.utils.Colors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * @author 'Zuhriddin Shamsiddinov' 9:50 PM - 6/20/2022 on Mon
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectCRUD {
    private static SubjectCRUD instance;

    private final SubjectService subjectService = ApplicationContextHolder.getBean(SubjectService.class);
    private final SubjectDAO subjectDAO = ApplicationContextHolder.getBean(SubjectDAO.class);
    private final UserDAO userDAO = ApplicationContextHolder.getBean(UserDAO.class);

    public void create() {
        List<User> teacherList = userDAO.teacherList();
        if (teacherList.size() == 0) {
            BaseUtils.println("You have not any teachers", Colors.RED);
            return;
        }
        teacherList.forEach(teacher -> BaseUtils.println("%s :  %s   %s".formatted(
                teacher.getUsername(), teacher.getEmail(), teacher.getPosition())));
        String teacherName = BaseUtils.readText("Enter teacher name for which you create subject : ");
        User teacher = userDAO.findByUserName(teacherName);
        if (Objects.isNull(teacher)) {
            BaseUtils.println("Not found teacher", Colors.RED);
            return;
        }
        String name = BaseUtils.readText("Enter subject name : ");
        Subject bySubjectName = subjectDAO.findBySubjectName(name);
        if (!Objects.isNull(bySubjectName)) {
            BaseUtils.println("Subject name already taken a '%s'".formatted(name), Colors.RED);
            return;
        }
        String description = BaseUtils.readText("Enter subject description : ");
        Subject subject = new Subject(name, description, teacher.getId());
        subjectService.create(subject);
        BaseUtils.println("Successfully created", Colors.GREEN);

    }

    public void delete() {
        subjectList();
        String username = BaseUtils.readText("Enter name of subject for delete : ");
        subjectService.deleteByName(username);
    }

    public void update() {
        subjectList();
        String name = BaseUtils.readText("Enter name of subject for update : ");
        Subject byId = subjectDAO.findBySubjectName(name);
        if (Objects.isNull(byId)) {
            BaseUtils.println("Not found Subject by id '%s'".formatted(name), Colors.RED);
        } else {
            String newName = BaseUtils.readText("Enter subject newName : ");
            String description = BaseUtils.readText("Enter subject description : ");
            Subject subject = new Subject(newName, description, byId.getTeacherId());
            subjectService.update(subject, name);
            BaseUtils.println("Successfully updated", Colors.GREEN);
        }
    }

    public void subjectList() {
        subjectService.subjectList();
    }

    public void logout() {
        subjectService.persist();
        BaseUtils.println("Successfully logged out");
        AdminMenu.adminMenu();
    }

    public static SubjectCRUD getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SubjectCRUD();
        }
        return instance;
    }
}
