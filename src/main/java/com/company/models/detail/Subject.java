package com.company.models.detail;

import com.company.models.base.BaseModel;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Subject extends BaseModel {
    private String name;
    private String description;
    private long teacherId;
}
