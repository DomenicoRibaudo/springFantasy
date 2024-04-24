package com.develhope.springFantasy.enums;

public enum FightEnum {

    PHYSICAL("physical"),
    MAGICAL("magical");

    private final String fight;

    FightEnum(String fight) {
        this.fight = fight;
    }

    public String getFight() {
        return fight;
    }
}
