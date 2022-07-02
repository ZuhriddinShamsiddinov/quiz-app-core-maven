package com.company.models.quiz;

import com.company.models.base.BaseModel;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizQuestions extends BaseModel {
    private long quizId;
    private long questionId;
    private boolean checked;
}
