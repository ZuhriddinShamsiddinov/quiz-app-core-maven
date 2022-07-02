package com.company.menu;

import com.company.crud.QuestionCRUD;
import com.company.crud.QuizCRUD;
import com.company.crud.SubjectCRUD;
import com.company.crud.UserCRUD;
import com.company.mapper.ApplicationContextHolder;
import com.company.service.auth.UserService;
import com.company.ui.UI;
import com.company.utils.BaseUtils;
import com.company.utils.Colors;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @created 4:10 PM on 6/20/2022 on Monday
 * @project quiz-app-maven
 */
public class AdminMenu {

    private static final SubjectCRUD SUBJECT_CRUD = ApplicationContextHolder.getBean(SubjectCRUD.class);
    private static final UserCRUD USER_CRUD = ApplicationContextHolder.getBean(UserCRUD.class);
    private static final QuizCRUD QUIZ_CRUD = ApplicationContextHolder.getBean(QuizCRUD.class);
    private static final QuestionCRUD QUESTION_CRUD = ApplicationContextHolder.getBean(QuestionCRUD.class);
    private static final UserService USER_SERVICE = ApplicationContextHolder.getBean(UserService.class);

    public static void adminMenu() {
        BaseUtils.println("Users -> 1");
        BaseUtils.println("Teacher -> 2");
        BaseUtils.println("Subject -> 3");
        BaseUtils.println("Questions -> 5");
        BaseUtils.println("Quiz -> 4");
        BaseUtils.println("Logout -> 0");

        String choice = BaseUtils.readText("Enter choice : ");
        switch (choice) {
            case "1" -> userCRUD();
            case "2" -> teacherCRUD();
            case "3" -> subjectCRUD();
            case "4" -> quizCRUD();
            case "5" -> questionCRUD();
            case "0" -> logout();
        }
        adminMenu();
    }

    public static void logout() {
        USER_SERVICE.persist();
        BaseUtils.println("Successfully logged out", Colors.GREEN);
        UI.isSigned = false;
        MainMenu.menu();
    }


    private static void questionCRUD() {
        BaseUtils.println("Create question -> 1", Colors.GREEN);
        BaseUtils.println("Delete question -> 2", Colors.GREEN);
        BaseUtils.println("Update question -> 3", Colors.GREEN);
        BaseUtils.println("Question List -> 4", Colors.GREEN);
        BaseUtils.println("Back -> 0", Colors.GREEN);
        String choice = BaseUtils.readText("Enter choice : ");
        switch (choice) {
            case "1" -> QUESTION_CRUD.create();
            case "2" -> QUESTION_CRUD.delete();
            case "3" -> QUESTION_CRUD.update();
            case "4" -> QUESTION_CRUD.questionList();
            case "0" -> QUESTION_CRUD.logout();
        }
        questionCRUD();
    }

    private static void quizCRUD() {
        BaseUtils.println("Create quiz -> 1", Colors.BLACK);
        BaseUtils.println("Delete quiz -> 2", Colors.BLACK);
        BaseUtils.println("Update quiz -> 3", Colors.BLACK);
        BaseUtils.println("Quiz List -> 4", Colors.BLACK);
        BaseUtils.println("Get one quiz -> 5", Colors.BLACK);
        BaseUtils.println("Back -> 0", Colors.BLACK);
        String choice = BaseUtils.readText("Enter choice : ");
        switch (choice) {
            case "1" -> QUIZ_CRUD.create();
            case "2" -> QUIZ_CRUD.delete();
            case "3" -> QUIZ_CRUD.update();
            case "4" -> QUIZ_CRUD.quizList();
            case "5" -> QUIZ_CRUD.getOne();
            case "0" -> QUIZ_CRUD.logout();
        }
        quizCRUD();
    }

    private static void subjectCRUD() {
        BaseUtils.println("Create subject -> 1", Colors.PURPLE);
        BaseUtils.println("Delete subject -> 2", Colors.PURPLE);
        BaseUtils.println("Update subject -> 3", Colors.PURPLE);
        BaseUtils.println("Subject List -> 4", Colors.PURPLE);
        BaseUtils.println("Back -> 0", Colors.PURPLE);
        String choice = BaseUtils.readText("Enter choice : ");
        switch (choice) {
            case "1" -> SUBJECT_CRUD.create();
            case "2" -> SUBJECT_CRUD.delete();
            case "3" -> SUBJECT_CRUD.update();
            case "4" -> SUBJECT_CRUD.subjectList();
            case "0" -> SUBJECT_CRUD.logout();
            default -> BaseUtils.println("Wrong choice", Colors.RED);
        }
        subjectCRUD();
    }


    private static void teacherCRUD() {
        BaseUtils.println("Set Role  ->  1");
        BaseUtils.println("Teacher List ->  2");
        BaseUtils.println("Remove from Teacher ->  3");
        BaseUtils.println("Back ->  0");
        String choice = BaseUtils.readText("Enter choice : ");
        switch (choice) {
            case "1" -> USER_CRUD.setRole();
            case "2" -> USER_CRUD.teacherList();
            case "3" -> USER_CRUD.revokeRole();
            case "0" -> USER_CRUD.logout();
        }
        teacherCRUD();
    }

    private static void userCRUD() {
        BaseUtils.println("Create user -> 1", Colors.WHITE);
        BaseUtils.println("Delete user -> 2", Colors.WHITE);
        BaseUtils.println("Update user -> 3", Colors.WHITE);
        BaseUtils.println("Users List -> 4", Colors.WHITE);
        BaseUtils.println("Back -> 0", Colors.WHITE);
        String choice = BaseUtils.readText("Enter choice : ");
        switch (choice) {
            case "1" -> USER_CRUD.create();
            case "2" -> USER_CRUD.delete();
            case "3" -> USER_CRUD.update();
            case "4" -> USER_CRUD.userList();
            case "0" -> USER_CRUD.logout();
        }
        userCRUD();
    }
}
