package com.company.dao.quiz;


import com.company.dao.base.BaseDAO;
import com.company.models.quiz.Quiz;
import com.google.gson.reflect.TypeToken;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author 'Zuhriddin Shamsiddinov' 3:02 PM - 6/21/2022 on Tue
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuizDAO extends BaseDAO {
    private static QuizDAO instance;

    private final List<Quiz> quizList = load();


    public void save(Quiz quiz) {
        quiz.setId(System.currentTimeMillis());
        quizList.add(quiz);
    }


    public List<Quiz> findAll() {
        return quizList;
    }

    private List<Quiz> load() {
        try (InputStream is = new FileInputStream("src/main/resources/db/quiz.json")) {
            String jsonSTRING = new String(is.readAllBytes());
            return gson.fromJson(jsonSTRING, new TypeToken<List<Quiz>>() {
            }.getType());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void persist() {
        try (OutputStream is = new FileOutputStream("src/main/resources/db/quiz.json")) {
            String jsonSTRING = gson.toJson(quizList);
            is.write(jsonSTRING.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public static QuizDAO getInstance() {
        if (Objects.isNull(instance)) {
            instance = new QuizDAO();
        }
        return instance;
    }
}
