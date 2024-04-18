package com.develhope.springFantasy.services;

import com.develhope.springFantasy.entities.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;


public class FightService {

    public String characterFight(Character attacker, Character defender) {
        Integer physicalDamage = calculatePhysicalDamage(attacker);
        defender.setLifePoints(defender.getLifePoints() - physicalDamage);
        if(defender.isAlive(false)){
            String message = "Defender defeated";
            return message;
        } else {
            String message2 = "Defender is still alive with " + defender.getLifePoints();
            return message2;
        }

    }

    //Utility methods
    private Integer calculatePhysicalDamage(Character attacker) {
        Random random = new Random();
        Integer multiplierDamage = random.nextInt(10) + 1;
        Integer physicalDamage = attacker.getDamage() * multiplierDamage + attacker.getPhysicalStrength();
        return physicalDamage;
    }

    private void isAlive(Character defender) {
        if(defender.getLifePoints() <= 0) {
            defender.setAlive(false);
        }

    }
}
