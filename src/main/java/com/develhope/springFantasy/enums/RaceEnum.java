package com.develhope.springFantasy.enums;

public enum RaceEnum {
    HUMAN("HUMAN"),
    ORC("ORC"),
    ELF("ELF"),
    GNOME("GNOME"),;

    private final String race;
    RaceEnum(String race) {
        this.race = race;
    }

    public String getRace() {
        return race;
    }
}
