package com.company.mapper;

import com.company.crud.QuestionCRUD;
import com.company.crud.QuizCRUD;
import com.company.crud.SubjectCRUD;
import com.company.crud.UserCRUD;
import com.company.dao.auth.UserDAO;
import com.company.dao.detail.AnswerDAO;
import com.company.dao.detail.QuestionDAO;
import com.company.dao.detail.SubjectDAO;
import com.company.dao.quiz.QuizDAO;
import com.company.dao.quiz.QuizQuestionDAO;
import com.company.service.auth.UserService;
import com.company.service.detail.QuestionService;
import com.company.service.detail.SubjectService;
import com.company.service.quiz.QuizService;

/**
 * @author 'Zuhriddin Shamsiddinov' 4:29 PM - 6/22/2022 on Wed
 */
public class ApplicationContextHolder {
    @SuppressWarnings("unchecked")
    private static <T> T getBean(String beanName) {
        return switch (beanName) {
            case "UserDAO" -> (T) UserDAO.getInstance();
            case "SubjectDAO" -> (T) SubjectDAO.getInstance();
            case "UserService" -> (T) UserService.getInstance();
            case "SubjectService" -> (T) SubjectService.getInstance();
            case "QuestionService" -> (T) QuestionService.getInstance();
            case "QuestionDAO" -> (T) QuestionDAO.getInstance();
            case "QuizQuestionDAO" -> (T) QuizQuestionDAO.getInstance();
            case "AnswerDAO" -> (T) AnswerDAO.getInstance();
            case "SubjectCRUD" -> (T) SubjectCRUD.getInstance();
            case "UserCRUD" -> (T) UserCRUD.getInstance();
            case "QuestionCRUD" -> (T) QuestionCRUD.getInstance();
            case "QuizCRUD" -> (T) QuizCRUD.getInstance();
            case "QuizService" -> (T) QuizService.getInstance();
            case "QuizDAO" -> (T) QuizDAO.getInstance();
            default -> throw new RuntimeException("Bean Not Found");
        };
    }

    public static <T> T getBean(Class<T> clazz) {
        String beanName = clazz.getSimpleName();
        return getBean(beanName);
    }
}
