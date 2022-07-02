package com.company.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 'Zuhriddin Shamsiddinov' 9:14 AM - 6/21/2022 on Tue
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {
    private String username;
    private String email;
    private String language;
    private String position;
}
