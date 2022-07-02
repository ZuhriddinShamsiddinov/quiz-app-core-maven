package com.company.dao.detail;

import com.company.dao.base.BaseDAO;
import com.company.models.detail.Answer;
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
 * @author 'Zuhriddin Shamsiddinov' 9:51 PM - 6/22/2022 on Wed
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnswerDAO extends BaseDAO {
    private final List<Answer> answerList = load();
    private static AnswerDAO instance;


    public void save(Answer answer) {
        answer.setId(System.currentTimeMillis());
        answerList.add(answer);
    }

    public List<Answer> findByQuestionId(Long id) {
        return answerList.stream()
                .filter(answer -> answer.getQuestionId() == id)
                .collect(Collectors.toList());
    }


    private List<Answer> load() {
        try (FileReader fileReader = new FileReader("src/main/resources/db/answer.json");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String jsonSTRING = bufferedReader.lines().collect(Collectors.joining());
            return gson.fromJson(jsonSTRING, new TypeToken<List<Answer>>() {
            }.getType());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void persist() {
        try (FileWriter fileWriter = new FileWriter("src/main/resources/db/answer.json")) {
            fileWriter.write(gson.toJson(answerList));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static AnswerDAO getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AnswerDAO();
        }
        return instance;
    }
}
