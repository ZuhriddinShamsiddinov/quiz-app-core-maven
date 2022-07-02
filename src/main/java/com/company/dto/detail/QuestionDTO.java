package com.company.dto.detail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 'Zuhriddin Shamsiddinov' 5:45 PM - 6/22/2022 on Wed
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
    private String body;
    private long subjectId;
    private String level;
    private String language;
}
