package com.company.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author 'Zuhriddin Shamsiddinov' 8:48 AM - 6/21/2022 on Tue
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
    private String username;
    private String password;
}
