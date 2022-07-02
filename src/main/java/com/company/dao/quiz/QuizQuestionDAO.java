package com.company.dao.quiz;

import com.company.dao.base.BaseDAO;
import com.company.models.quiz.QuizQuestions;
import com.google.gson.reflect.TypeToken;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 'Zuhriddin Shamsiddinov' 3:02 PM - 6/21/2022 on Tue
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuizQuestionDAO extends BaseDAO {
    private static QuizQuestionDAO instance;
    private final List<QuizQuestions> quizQuestionsList = load();

    public void save(QuizQuestions quizQuestions) {
        quizQuestions.setId(System.currentTimeMillis());
        quizQuestionsList.add(quizQuestions);
    }

    private List<QuizQuestions> load() {
        try (FileReader fileReader = new FileReader("src/main/resources/db/quizquestions.json");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String jsonSTRING = bufferedReader.lines().collect(Collectors.joining());
            return gson.fromJson(jsonSTRING, new TypeToken<List<QuizQuestions>>() {
            }.getType());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void persist() {
        try (FileWriter fileWriter = new FileWriter("src/main/resources/db/quizquestions.json")) {
            fileWriter.write(gson.toJson(quizQuestionsList));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static QuizQuestionDAO getInstance() {
        if (Objects.isNull(instance)) {
            instance = new QuizQuestionDAO();
        }
        return instance;
    }
}
