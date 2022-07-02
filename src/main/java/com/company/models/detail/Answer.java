package com.company.models.detail;

import com.company.models.base.BaseModel;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Answer extends BaseModel {
    private char variant;
    private String body;
    private boolean right;
    private long questionId;
}
