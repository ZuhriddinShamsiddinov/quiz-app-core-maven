package com.company.crud;

import com.company.dao.detail.QuestionDAO;
import com.company.dao.detail.SubjectDAO;
import com.company.dto.detail.QuestionDTO;
import com.company.enums.Language;
import com.company.enums.Level;
import com.company.mapper.ApplicationContextHolder;
import com.company.menu.AdminMenu;
import com.company.models.detail.Answer;
import com.company.models.detail.Question;
import com.company.models.detail.Subject;
import com.company.service.detail.QuestionService;
import com.company.utils.BaseUtils;
import com.company.utils.Colors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @author 'Zuhriddin Shamsiddinov' 10:52 PM - 6/20/2022 on Mon
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionCRUD {

    private static QuestionCRUD instance;

    private final SubjectCRUD subjectCRUD = ApplicationContextHolder.getBean(SubjectCRUD.class);
    private final SubjectDAO subjectDAO = ApplicationContextHolder.getBean(SubjectDAO.class);
    private final QuestionService questionService = ApplicationContextHolder.getBean(QuestionService.class);
    private final QuestionDAO questionDAO = ApplicationContextHolder.getBean(QuestionDAO.class);

    public void update() {
        questionList();
        long id = Long.parseLong(BaseUtils.readText("Enter id of question : "));
        Optional<Question> questionOptional = questionDAO.findById(id);
        if (questionOptional.isEmpty()) {
            BaseUtils.println("Not found question : ", Colors.RED);
            return;
        }
        subjectCRUD.subjectList();
        long sId = Long.parseLong(BaseUtils.readText("Enter id of question : "));
        Optional<Subject> subjectOptional = subjectDAO.findById(sId);
        if (subjectOptional.isEmpty()) {
            BaseUtils.println("Subject not found", Colors.RED);
            return;
        }
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setSubjectId(sId);
        questionDTO.setBody(BaseUtils.readText("Enter new body for update : "));
        questionDTO.setLanguage(BaseUtils.readText("Enter new language : "));
        questionDTO.setLevel(BaseUtils.readText("Enter new level : "));
        questionService.update(questionDTO, id);
    }

    public void create() {
        subjectCRUD.subjectList();
        String name = BaseUtils.readText("Enter the name of subject which you add question : ");
        Subject subject = subjectDAO.findBySubjectName(name);
        if (Objects.isNull(subject)) {
            BaseUtils.println("Not found subject", Colors.RED);
        } else {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setBody(BaseUtils.readText("Enter question body : "));
            questionDTO.setLanguage(BaseUtils.readText("Enter question language : "));
            questionDTO.setLevel(BaseUtils.readText("Enter question level : "));
            questionDTO.setSubjectId(subject.getId());
            questionService.save(questionDTO);
        }
    }

    public void delete() {
        questionList();
        long id = Long.parseLong(BaseUtils.readText("Enter id of question : "));
        questionService.delete(id);
    }

    public void questionList() {
        questionService.getAll();
    }

    public void logout() {
        questionService.persist();
        BaseUtils.println("Successfully logged out");
        AdminMenu.adminMenu();
    }

    public static QuestionCRUD getInstance() {
        if (Objects.isNull(instance)) {
            instance = new QuestionCRUD();
        }
        return instance;
    }
}
