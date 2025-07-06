package org.flyingsparrow.utils;

public enum DateTimePatternEunm {
    YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"), YYYY_MM_DD("yyyy-MM-dd");

    private String pattern;

    DateTimePatternEunm(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

}
