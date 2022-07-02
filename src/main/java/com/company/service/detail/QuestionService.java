package com.company.service.detail;

import com.company.dao.detail.AnswerDAO;
import com.company.dao.detail.QuestionDAO;
import com.company.dto.detail.QuestionDTO;
import com.company.enums.Language;
import com.company.enums.Level;
import com.company.mapper.ApplicationContextHolder;
import com.company.models.detail.Answer;
import com.company.models.detail.Question;
import com.company.utils.BaseUtils;
import com.company.utils.Colors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 'Zuhriddin Shamsiddinov' 5:43 PM - 6/22/2022 on Wed
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionService {
    private static QuestionService instance;
    private final QuestionDAO questionDAO =
            ApplicationContextHolder.getBean(QuestionDAO.class);
    private final AnswerDAO answerDAO = ApplicationContextHolder.getBean(AnswerDAO.class);

    public void save(QuestionDTO questionDTO) {
        Question question = new Question();
        question.setBody(questionDTO.getBody());
        question.setSubjectId(questionDTO.getSubjectId());
        question.setLanguage(Language.findByName(questionDTO.getLanguage()));
        question.setLevel(Level.findByName(questionDTO.getLevel()));
        questionDAO.save(question);
        answerCreate(question);
        System.out.println("Successfully created question");
    }

    public void update(QuestionDTO questionDTO, Long id) {
        Optional<Question> questionOptional = questionDAO.findById(id);
        Question question = questionOptional.get();
        question.setSubjectId(questionDTO.getSubjectId());
        question.setLevel(Level.findByName(questionDTO.getLevel()));
        question.setLanguage(Language.findByName(questionDTO.getLanguage()));
        question.setBody(questionDTO.getBody());

    }

    public void delete(Long id) {
        Optional<Question> questionOptional = questionDAO.findById(id);
        if (questionOptional.isEmpty()) {
            BaseUtils.println("Not found question : ", Colors.RED);
            return;
        }
        questionDAO.deleteById(id);
        BaseUtils.println("Successfully deleted");
    }

    public void getAll() {
        List<Question> questionList = questionDAO.findAll();
        AtomicInteger counter = new AtomicInteger(1);
        questionList.
                forEach(question -> BaseUtils.
                        println("%s .  %s  |  %s  |   %s   ->  %s".
                                formatted(counter.getAndIncrement(), question.getBody(), question.getLanguage(),
                                        question.getLevel(), question.getSubjectId())));
    }

    public void answerCreate(Question question) {
        BaseUtils.println("---------- Add question answers ----------", Colors.GREEN);
        List<Answer> answers = new ArrayList<>(4);
        char[] variants = {'-', 'A', 'B', 'C', 'D'};
        for (int i = 1; i <= 4; i++) {
            Answer answer = new Answer();
            answer.setQuestionId(question.getId());
            answer.setRight(false);
            answer.setVariant(variants[i]);
            answer.setBody(BaseUtils.readText("%s ) : ?".formatted(variants[i])));
            answerDAO.save(answer);
            answers.add(answer);
        }
        String var = BaseUtils.readText("Enter right variant : ");
        var = var.toUpperCase();
        char[] rightVariant = var.toCharArray();
        for (Answer answer : answers) {
            if (answer.getVariant() == rightVariant[0]) {
                answer.setRight(true);
                BaseUtils.println("Successfully created");
                return;
            }
        }
    }

    public void answerUpdate(Question question) {
        BaseUtils.println("---------- Update question answers ----------", Colors.GREEN);
        List<Answer> answerList = answerDAO.findByQuestionId(question.getId());
        char[] variants = {'-', 'A', 'B', 'C', 'D'};
        for (int i = 0; i < answerList.size(); i++) {
            Answer answer = answerList.get(i);
            answer.setQuestionId(question.getId());
            answer.setRight(false);
            answer.setVariant(variants[i]);
            answer.setBody(BaseUtils.readText("%s ) ? : ".formatted(variants[i])));
        }

        String var = BaseUtils.readText("Enter right variant : ");
        char[] rightVariant = var.toCharArray();
        for (Answer answer : answerList) {
            if (answer.getVariant() == rightVariant[0]) {
                answer.setRight(true);
                BaseUtils.println("Successfully created");
                return;
            }
        }
    }

    public static QuestionService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new QuestionService();
        }
        return instance;
    }

    public void persist() {
        answerDAO.persist();
        questionDAO.persist();
    }
}
