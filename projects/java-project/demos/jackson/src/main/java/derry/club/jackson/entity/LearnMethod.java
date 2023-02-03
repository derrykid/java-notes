package derry.club.jackson.entity;

import lombok.ToString;

public enum LearnMethod {
    UNKNOWN("unknown"),
    EGG("egg"),
    MACHINE("machine"),
    LEVEL_UP("level-up"),
    TUTOR("tutor");

    private final String name;

    LearnMethod(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
