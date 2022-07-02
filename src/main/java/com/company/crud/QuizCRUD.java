package com.company.crud;

import com.company.dto.quiz.QuizDTO;
import com.company.mapper.ApplicationContextHolder;
import com.company.menu.AdminMenu;
import com.company.service.quiz.QuizService;
import com.company.ui.UI;
import com.company.utils.BaseUtils;
import com.company.utils.Colors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author 'Zuhriddin Shamsiddinov' 10:51 PM - 6/20/2022 on Mon
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuizCRUD {
    private final QuizService quizService = ApplicationContextHolder.getBean(QuizService.class);
    private static QuizCRUD instance;

    public void create() {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setUserId(UI.session.getId());
        quizDTO.setLevel(BaseUtils.readText("Enter quiz level : "));
        quizDTO.setSubject(BaseUtils.readText("Enter subject name which you add : "));
        quizService.save(quizDTO);
    }

    public void delete() {

    }

    public void update() {

    }

    public void quizList() {
        quizService.getAll();
    }

    public void logout() {
        quizService.persist();
        BaseUtils.println("Successfully logged out", Colors.GREEN);
        AdminMenu.adminMenu();
    }

    public void getOne() {
        quizService.getOne();
    }

    public static QuizCRUD getInstance() {
        if (Objects.isNull(instance)) {
            instance = new QuizCRUD();
        }
        return instance;
    }
}
