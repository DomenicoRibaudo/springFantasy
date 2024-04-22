package com.develhope.springFantasy.services;

import com.develhope.springFantasy.entities.Character;
import com.develhope.springFantasy.enums.FightEnum;
import com.develhope.springFantasy.enums.RaceEnum;
import com.develhope.springFantasy.enums.SpecialMovesEnum;
import com.develhope.springFantasy.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {
    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private FightUtilities fightUtilities;

    public Character createCharacter(Character character) {
        return characterRepository.save(character);
    }

    public Optional<Character> getCharacterById(Long id) {
        return characterRepository.findById(id);
    }

    public Optional<Character> updateCharacter(Long id, Character character) {
        Optional<Character> characterOptional = characterRepository.findById(id);
        if (characterOptional.isPresent()) {
            characterOptional.get().setDescription(character.getDescription());
            characterOptional.get().setRace(character.getRace());
            characterOptional.get().setName(character.getName());
            characterOptional.get().setPhysicalStrength(character.getPhysicalStrength());
            characterOptional.get().setCharacterClassEnum(character.getCharacterClassEnum());
            characterOptional.get().setLevel(character.getLevel());
            characterOptional.get().setLifePoints(character.getLifePoints());
            Character characterUpdated = characterRepository.save(characterOptional.get());
            return Optional.of(characterUpdated);
        }
        return Optional.empty();
    }

    public Optional<Character> deleteCharacter(Long id) {
        Optional<Character> characterOptional = characterRepository.findById(id);
        if (characterOptional.isPresent()) {
            characterRepository.delete(characterOptional.get());
        } else {
            return Optional.empty();
        }
        return characterOptional;

    }

    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    public List<Character> getCharactersByName(String inputValue) {
        return characterRepository.findCharacterByName(inputValue);
    }

    public List<Character> getCharactersByRace(RaceEnum inputValue) {
        return characterRepository.findCharacterByRace(inputValue);
    }

    public String characterFight(Long defenderId, Long attackerId, FightEnum fightEnum, SpecialMovesEnum specialMovesEnum) throws Exception {
        Optional<Character> defenderOptional = characterRepository.findById(defenderId);
        Optional<Character> attackerOptional = characterRepository.findById(attackerId);
        String result = "";
        if(defenderOptional.isPresent() && attackerOptional.isPresent()) {
            if (fightEnum.equals(FightEnum.PHYSICAL)){
                result = fightUtilities.characterPhysicalFight(attackerOptional.get(), defenderOptional.get());
            } else {
                result = fightUtilities.characterMagicalFight(attackerOptional.get(), defenderOptional.get(), specialMovesEnum);
            }
            characterRepository.save(defenderOptional.get());
        }
        return result;
    }

    public String characterMagicFight(Character attackCharacter, Long defenderId, SpecialMovesEnum specialAttack) throws Exception {
        Optional<Character> defenderOptional = characterRepository.findById(defenderId);
        String result = "";
        if (defenderOptional.isPresent()) {
            result = fightUtilities.characterMagicalFight(attackCharacter, defenderOptional.get(), specialAttack);
            characterRepository.save(defenderOptional.get());
            characterRepository.save(attackCharacter);
        }
        return result;
    }

}
