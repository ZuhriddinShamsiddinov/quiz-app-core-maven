package com.company.dao.detail;

import com.company.dao.base.BaseDAO;
import com.company.enums.Level;
import com.company.models.detail.Question;
import com.google.gson.reflect.TypeToken;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 'Zuhriddin Shamsiddinov' 5:17 PM - 6/22/2022 on Wed
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionDAO extends BaseDAO {
    private List<Question> questionList = load();
    private static QuestionDAO instance;


    public void save(Question question) {
        question.setId(System.currentTimeMillis());
        questionList.add(question);
    }

    public void update(Question newQuestion, Long id) {
        for (Question oldQuestion : questionList) {
            if (Objects.equals(oldQuestion.getId(), id)) {
                oldQuestion.setBody(newQuestion.getBody());
                oldQuestion.setLanguage(newQuestion.getLanguage());
                oldQuestion.setLevel(newQuestion.getLevel());
                oldQuestion.setSubjectId(newQuestion.getSubjectId());
                return;
            }
        }
    }

    public Optional<Question> findById(Long id) {
        for (Question question : questionList) {
            if (Objects.equals(question.getId(), id)) {
                return Optional.of(question);
            }
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        for (Question question : questionList) {
            if (Objects.equals(question.getId(), id)) {
                questionList.remove(question);
                return;
            }
        }
    }

    public Optional<Question> findByBody(String body) {
        for (Question question : questionList) {
            if (body.equals(question.getBody())) {
                return Optional.of(question);
            }
        }
        return Optional.empty();
    }

    public List<Question> findAll() {
        return questionList;
    }

    public List<Question> findAllByLevel(String level) {
        return questionList.stream()
                .filter(question -> question.getLevel().equals(Level.findByName(level)))
                .collect(Collectors.toList());
    }

    private List<Question> load() {
        try (FileReader fileReader = new FileReader("src/main/resources/db/question.json");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String jsonSTRING = bufferedReader.lines().collect(Collectors.joining());
            return gson.fromJson(jsonSTRING, new TypeToken<List<Question>>() {
            }.getType());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void persist() {
        try (FileWriter fileWriter = new FileWriter("src/main/resources/db/question.json")) {
            fileWriter.write(gson.toJson(questionList));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static QuestionDAO getInstance() {
        if (Objects.isNull(instance)) {
            instance = new QuestionDAO();
        }
        return instance;
    }
}
