package com.company.config;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author 'Zuhriddin Shamsiddinov' 9:43 AM - 6/23/2022 on Thu
 */
public class PasswordConfigurer {
    public static String encode(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(12));
    }

    public static boolean matchPassword(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
