package com.develhope.springFantasy.entities;

import com.develhope.springFantasy.enums.CharacterClassEnum;
import com.develhope.springFantasy.enums.RaceEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    @Column
    private CharacterClassEnum characterClassEnum;


    @Enumerated(EnumType.STRING)
    @Column
    private RaceEnum race;

    @Column
    private Integer level;

    @Column
    private Integer physicalStrength;

    @Column
    private String description;

    @Column
    private Integer lifePoints;

    @Column
    private Integer manaPoints;

    @Column
    private boolean isAlive;



    public Character() {
    }


    public Character(Long id, String name, CharacterClassEnum characterClassEnum, RaceEnum race, Integer level, Integer physicalStrength, String description, Integer lifePoints, Integer manaPoints, boolean isAlive) {
        this.id = id;
        this.name = name;
        this.characterClassEnum = characterClassEnum;
        this.race = race;
        this.level = level;
        this.physicalStrength = physicalStrength;
        this.description = description;
        this.lifePoints = lifePoints;
        this.manaPoints = manaPoints;
        this.isAlive = isAlive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterClassEnum getCharacterClassEnum() {
        return characterClassEnum;
    }

    public void setCharacterClassEnum(CharacterClassEnum characterClassEnum) {
        this.characterClassEnum = characterClassEnum;
    }

    public RaceEnum getRace() {
        return race;
    }

    public void setRace(RaceEnum race) {
        this.race = race;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPhysicalStrength() {
        return physicalStrength;
    }

    public void setPhysicalStrength(Integer physicalStrength) {
        this.physicalStrength = physicalStrength;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(Integer lifePoints) {
        this.lifePoints = lifePoints;
    }

    public Integer getManaPoints() {
        return manaPoints;
    }

    public void setManaPoints(Integer manaPoints) {
        this.manaPoints = manaPoints;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
