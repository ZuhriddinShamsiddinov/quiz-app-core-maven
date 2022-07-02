package com.company.enums;

public enum Level {
    EASY, MEDIUM, HARD;

    public static Level findByName(String level) {
        for (Level lev : values()) {
            if (lev.name().equalsIgnoreCase(level))
                return lev;
        }
        return EASY;
    }
}
