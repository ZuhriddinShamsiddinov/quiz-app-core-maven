package com.company.models.auth;

import com.company.enums.Language;
import com.company.enums.Position;
import com.company.models.base.BaseModel;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseModel {
    private String username;
    private String password;
    private Language language;
    private String email;
    private Position position;
    private int loginTryCount;
    private boolean locked;
    private Date lockedTill;

    public User(String username, String password, String email, Position position) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.position = position;
    }
}
