package com.develhope.springFantasy.services;

import com.develhope.springFantasy.entities.Character;
import com.develhope.springFantasy.enums.SpecialMovesEnum;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service

public class FightUtilities {

    public String characterPhysicalFight(Character attacker, Character defender) {
        Integer physicalDamage = calculatePhysicalDamage(attacker);
        defender.setLifePoints(defender.getLifePoints() - physicalDamage);
        checkLifeCharacter(defender);
        if (!defender.isAlive()) {
            String message = defender.getName() + " defeated";
            return message;
        } else {
            String message2 = defender.getName() + " is still alive with " + defender.getLifePoints();
            return message2;
        }

    }

    public String characterMagicalFight(Character attacker, Character defender, SpecialMovesEnum specialAttack) throws Exception {
        Integer specialDamage = calculateMagicalDamage(attacker, specialAttack);
        defender.setLifePoints(defender.getLifePoints() - specialDamage);
        //Riduci il mana del costo della skill attivata
        attacker.setManaPoints(attacker.getManaPoints() - specialAttack.getActivationCost());
        checkLifeCharacter(defender);
        if (!defender.isAlive()) {
            String message = defender.getName() + " defeated after special attack:" + specialAttack + " that made " + specialDamage + " damage" ;
            return message;
        } else {
            String message2 = defender.getName() + " is still alive with " + defender.getLifePoints() + " life points";
            defender.setLifePoints(defender.getLifePoints());
            return message2;
        }
    }

    private Integer calculateCritDamage(Integer baseDamage) {
        Random random = new Random();
        Integer finalDamage = baseDamage;
        Integer critChance = random.nextInt(1);

        if (critChance == 0) {
            Integer multiplierCritDamage = random.nextInt(10) + 1;
            finalDamage = multiplierCritDamage * baseDamage;
        }

        return finalDamage;
    }

    private Integer calculatePhysicalDamage(Character attacker) {
        Integer physicalDamage = calculateCritDamage(attacker.getPhysicalStrength());
        return physicalDamage;
    }

    private Integer calculateMagicalDamage(Character attacker, SpecialMovesEnum specialAttack) throws Exception {
        Integer magicDamage = calculateCritDamage(specialAttack.getDamage());

        if (attacker.getManaPoints() <= specialAttack.getActivationCost()) {
            throw new Exception("You don't have enough mana, select other moves");
        }
        return magicDamage;
    }



    //Utility methods
    public void checkLifeCharacter(Character defender) {
        if (defender.getLifePoints() <= 0) {
            defender.setAlive(false);
        }
    }

}

