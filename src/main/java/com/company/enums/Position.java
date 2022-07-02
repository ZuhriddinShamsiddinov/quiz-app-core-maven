package com.company.enums;

public enum Position {
    ADMIN, TEACHER, USER;

    public static Position findByName(String position) {
        for (Position pos : values()) {
            if (pos.name().equalsIgnoreCase(position))
                return pos;
        }
        return USER;
    }
}
