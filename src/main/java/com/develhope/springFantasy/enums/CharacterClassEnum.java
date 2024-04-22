package com.develhope.springFantasy.enums;

public enum CharacterClassEnum {
    WARRIOR("Warrior"),
    THIEF("Thief"),
    RANGER("Ranger"),
    WIZARD("Wizard"),
    ELF("Elf");

    private String className;

    CharacterClassEnum(String className){
        this.className = className;
    }

    public String getClassName(){
        return className;
    }
}
