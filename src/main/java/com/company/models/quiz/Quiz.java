package com.company.models.quiz;

import com.company.models.base.BaseModel;
import lombok.*;
import com.company.enums.Language;
import com.company.enums.Level;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz extends BaseModel {
    private Level level;
    private Language language;
    private long userId;
    private long subjectId;
    private int questionCount;
//    private LocalDateTime createAt;
    private int deadLineAmountInMinutes;
//    private LocalDateTime lockAt;
    private boolean completed;
}
