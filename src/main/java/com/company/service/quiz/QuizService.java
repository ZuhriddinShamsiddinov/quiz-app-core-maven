package com.company.service.quiz;

import com.company.dao.detail.AnswerDAO;
import com.company.dao.detail.QuestionDAO;
import com.company.dao.detail.SubjectDAO;
import com.company.dao.quiz.QuizDAO;
import com.company.dao.quiz.QuizQuestionDAO;
import com.company.dto.quiz.QuizDTO;
import com.company.enums.Language;
import com.company.enums.Level;
import com.company.mapper.ApplicationContextHolder;
import com.company.models.detail.Answer;
import com.company.models.detail.Question;
import com.company.models.detail.Subject;
import com.company.models.quiz.Quiz;
import com.company.models.quiz.QuizQuestions;
import com.company.service.detail.QuestionService;
import com.company.utils.BaseUtils;
import com.company.utils.Colors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 'Zuhriddin Shamsiddinov' 11:06 AM - 6/23/2022 on Thu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuizService {
    private static QuizService instance;
    private final QuizDAO quizDAO = ApplicationContextHolder.getBean(QuizDAO.class);
    private final QuizQuestionDAO quizQuestionDAO = ApplicationContextHolder.getBean(QuizQuestionDAO.class);
    private final SubjectDAO subjectDAO = ApplicationContextHolder.getBean(SubjectDAO.class);
    private final QuestionDAO questionDAO = ApplicationContextHolder.getBean(QuestionDAO.class);
    private final QuestionService questionService = ApplicationContextHolder.getBean(QuestionService.class);
    private final AnswerDAO answerDAO = ApplicationContextHolder.getBean(AnswerDAO.class);


    public void save(QuizDTO quizDTO) {
        Quiz quiz = new Quiz();
        Subject bySubjectName = subjectDAO.findBySubjectName(quizDTO.getSubject());
        if (Objects.isNull(bySubjectName)) {
            BaseUtils.println("Subject not found", Colors.RED);
            return;
        }
        quiz.setId(System.currentTimeMillis());
        quiz.setSubjectId(bySubjectName.getId());
        quiz.setLanguage(Language.findByName(quizDTO.getLanguage()));
        quiz.setUserId(quizDTO.getUserId());
        quiz.setLevel(Level.findByName(quizDTO.getLevel()));

        String level = Level.findByName(quizDTO.getLevel()).name();

        List<Question> questionList = questionDAO.findAllByLevel(level);
        if (Objects.isNull(questionList)) {
            BaseUtils.println("You have not any question on this level -  '%s'".formatted(level), Colors.RED);
            return;
        }
        questionService.getAll();
        String indexes = BaseUtils.readText("Enter question indexes with (,) for add questions : ");
        String[] questionsIndex = indexes.split(",");
        int[] indexOfQuestions = Arrays.stream(questionsIndex).mapToInt(Integer::parseInt).toArray();
        for (int indexOfQuestion : indexOfQuestions) {
            QuizQuestions quizQuestion = new QuizQuestions();
            quizQuestion.setQuizId(quiz.getId());
            quizQuestion.setQuestionId(questionList.get(indexOfQuestion - 1).getId());
            quizQuestionDAO.save(quizQuestion);
        }
        quizDAO.save(quiz);
        BaseUtils.println("Successfully created", Colors.GREEN);
    }

    public void getAll() {
        List<Quiz> quizList = quizDAO.findAll();
        AtomicInteger counter = new AtomicInteger(1);
        for (Quiz quiz : quizList) {
            BaseUtils.println("----------------------QUIZ----------------------", Colors.RED);
            Optional<Subject> subjectOptional = subjectDAO.findById(quiz.getSubjectId());
            Subject subject = subjectOptional.get();
            BaseUtils.println(counter.getAndIncrement() + ".  SUBJECT --------     %s  :  LEVEL ----------    %s  ".formatted(subject.getName(), quiz.getLevel()));
        }
    }

    public void getOne() {
        getAll();
        int index = Integer.parseInt(BaseUtils.readText("Enter index of quiz : "));
        Quiz quiz = quizDAO.findAll().get(index - 1);
        List<Question> questionList = questionDAO.findAllByLevel(quiz.getLevel().name());
        Optional<Subject> subjectOptional = subjectDAO.findById(quiz.getSubjectId());
        Subject subject = subjectOptional.get();
        BaseUtils.println("%s   -------   %s\n\n".formatted(subject.getName(), subject.getDescription()));
        BaseUtils.println("LEVEL ----  %s".formatted(quiz.getLevel()));
        for (Question question : questionList) {
            BaseUtils.println(" ------ Questions -------");
            List<Answer> answerList = answerDAO.findByQuestionId(question.getId());
            BaseUtils.println("%s".formatted(question.getBody()));
            for (Answer answer : answerList) {
                BaseUtils.println("%s)  %s".formatted(answer.getVariant(), answer.getBody()));
            }
        }
    }

    public static QuizService getInstance() {
        if (Objects.isNull(instance)) {
            instance = new QuizService();
        }
        return instance;
    }

    public void persist() {
        quizQuestionDAO.persist();
        quizDAO.persist();
    }
}
