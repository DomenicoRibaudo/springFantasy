package com.develhope.springFantasy.services;

import com.develhope.springFantasy.entities.Character;
import com.develhope.springFantasy.enums.SpecialMovesEnum;
import com.develhope.springFantasy.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        checkLifeCharacter(defender);
        if (!defender.isAlive()) {
            String message = defender.getName() + " defeated after " + specialAttack + "that made " + specialDamage + " damage" ;
            return message;
        } else {
            String message2 = defender.getName() + " is still alive with " + defender.getLifePoints();
            defender.setLifePoints(defender.getLifePoints());
            return message2;
        }
    }

    private Integer calculatePhysicalDamage(Character attacker) {
        Random random = new Random();
        Integer multiplierCritDamage = random.nextInt(10) + 1;
        Integer physicalDamage = multiplierCritDamage * attacker.getPhysicalStrength();
        return physicalDamage;
    }

    private Integer calculateMagicalDamage(Character attacker, SpecialMovesEnum specialAttack) throws Exception {
        //Randomizza un moltiplicatore di crit randomized
        Random random = new Random();
        Integer multiplierCritDamage = random.nextInt(10) + 1;
        if (attacker.getManaPoints() <= specialAttack.getActivationCost()) {
            throw new Exception("You don't have enough mana");
        } else {
            Integer magicDamage = specialAttack.getDamage() * multiplierCritDamage;
            //Riduci il mana del costo della skill attivata
            attacker.setManaPoints(attacker.getManaPoints() - specialAttack.getActivationCost());
            return magicDamage;
        }
    }

    //Utility methods
    public void checkLifeCharacter(Character defender) {
        if (defender.getLifePoints() <= 0) {
            defender.setAlive(false);
        }
    }



}

