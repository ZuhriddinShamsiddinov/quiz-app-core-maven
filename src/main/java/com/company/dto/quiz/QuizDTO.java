package com.company.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 'Zuhriddin Shamsiddinov' 2:37 PM - 6/23/2022 on Thu
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    private String level;
    private String language;
    private long userId;
    private String subject;
}
