package com.company.enums;

public enum Language {
    RU, UZ, EN;

    public static Language findByName(String language) {
        for (Language lang : values()) {
            if (lang.name().equalsIgnoreCase(language))
                return lang;
        }
        return RU;
    }
}
