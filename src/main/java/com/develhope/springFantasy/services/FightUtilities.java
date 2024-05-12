package com.develhope.springFantasy.services;

import com.develhope.springFantasy.entities.Character;
import com.develhope.springFantasy.enums.SpecialMovesEnum;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FightUtilities {

    /**
     * Calculates the physical damage dealt by the attacker based on their physical strength.
     * @param attacker the character who is attacking
     * @return the physical damage dealt
     */
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

    /**
     * Calculates the magical damage dealt by the attacker based on their magical power and the type of the special attack.
     * @param attacker the character who is attacking
     * @param specialAttack the type of the special attack
     * @return the magical damage dealt
     * @throws Exception if the character does not have enough mana to cast the special attack
     */

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


    /**
     * Calculates the critical damage dealt by the attacker based on their physical strength.
     *
     * @param baseDamage the base damage of the attack
     * @return the critical damage dealt
     */
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

    /**
     * Calculates the physical damage dealt by the attacker based on their physical strength.
     *
     * @param attacker the character who is attacking
     * @return the physical damage dealt
     */
    private Integer calculatePhysicalDamage(Character attacker) {
        Integer physicalDamage = calculateCritDamage(attacker.getPhysicalStrength());
        return physicalDamage;
    }

    /**
     * Calculates the magical damage dealt by the attacker based on their magical power and the type of the special attack.
     *
     * @param attacker the character who is attacking
     * @param specialAttack the type of the special attack
     * @return the magical damage dealt
     * @throws Exception if the character does not have enough mana to cast the special attack
     */
    private Integer calculateMagicalDamage(Character attacker, SpecialMovesEnum specialAttack) throws Exception {
        Integer magicDamage = calculateCritDamage(specialAttack.getDamage());

        if (attacker.getManaPoints() <= specialAttack.getActivationCost()) {
            throw new Exception("You don't have enough mana, select other moves");
        }
        return magicDamage;
    }


    //Utility methods
    /**
     * Checks if the defender is still alive and sets their alive status to false if their life points are equal to or less than zero.
     *
     * @param defender the character being attacked
     */
    public void checkLifeCharacter(Character defender) {
        if (defender.getLifePoints() <= 0) {
            defender.setAlive(false);
        }
    }

}

