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


    /**
     * Updates an existing character in the database.
     *
     * @param id        the id of the character to update
     * @param character the updated character information
     * @return the updated character, or an empty optional if the character could not be found
     */
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


    /**
     * Deletes a character from the database.
     *
     * @param id the id of the character to delete
     * @return the deleted character, or an empty optional if the character could not be found
     */
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


    /**
     * Calculates the result of a fight between two characters.
     *
     * @param defenderId       the id of the character who is the defender
     * @param attackerId       the id of the character who is the attacker
     * @param fightEnum        the type of fight to be performed (physical or magical)
     * @param specialMovesEnum the special move to be used in the magical fight
     * @return a message indicating the result of the fight
     * @throws Exception if an error occurs during the fight
     */

    public String characterFight(Long defenderId, Long attackerId, FightEnum fightEnum, SpecialMovesEnum specialMovesEnum) throws Exception {
        Optional<Character> defenderOptional = characterRepository.findById(defenderId);
        Optional<Character> attackerOptional = characterRepository.findById(attackerId);
        String result = "";
        if (defenderOptional.isPresent() && attackerOptional.isPresent()) {
            if (fightEnum.equals(FightEnum.PHYSICAL)) {
                result = fightUtilities.characterPhysicalFight(attackerOptional.get(), defenderOptional.get());
            } else {
                result = fightUtilities.characterMagicalFight(attackerOptional.get(), defenderOptional.get(), specialMovesEnum);
            }
            characterRepository.save(defenderOptional.get());
        }
        return result;
    }

    /**
     * Calculates the result of a magical fight between a character and another character.
     *
     * @param attackCharacter  the character who is performing the attack
     * @param defenderId         the character who is the defender picked by ID
     * @param specialAttack    the special move to be used in the fight
     * @return a message indicating the result of the fight
     * @throws Exception if an error occurs during the fight
     */

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
