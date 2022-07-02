package com.company.models.detail;

import com.company.enums.Language;
import com.company.models.base.BaseModel;
import lombok.*;
import com.company.enums.Level;
    
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question extends BaseModel {
    private String body;
    private long subjectId;
    private Level level;
    private Language language;
}
