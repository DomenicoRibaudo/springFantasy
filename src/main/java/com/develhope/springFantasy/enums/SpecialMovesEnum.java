package com.develhope.springFantasy.enums;

public enum SpecialMovesEnum {
    FIREBALL(20, 50),
    LIGHTNING_STRIKE(25,70),
    ICE_SHARD(15,40),
    EARTHQUAKE(30, 80);

    private Integer activationCost;

    private Integer damage;

    SpecialMovesEnum(Integer activationCost, Integer damage) {
        this.activationCost = activationCost;
        this.damage = damage;
    }

    public Integer getActivationCost() {
        return activationCost;
    }

    public void setActivationCost(Integer activationCost) {
        this.activationCost = activationCost;
    }

    public Integer getDamage() {
        return damage;
    }

}
