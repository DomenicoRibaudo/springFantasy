package com.develhope.springFantasy.controllers;

import com.develhope.springFantasy.entities.Character;
import com.develhope.springFantasy.enums.RaceEnum;
import com.develhope.springFantasy.enums.SpecialMovesEnum;
import com.develhope.springFantasy.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/character")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @PostMapping("/create")
    public ResponseEntity<Character> create(Character character) {
        return ResponseEntity.ok().body(characterService.createCharacter(character));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Character>> showCharacters() {
        return ResponseEntity.ok().body(characterService.getAllCharacters());
    }

    @GetMapping("/single/{id}")
    public ResponseEntity<Character> showSingleCharacter(@PathVariable Long id) {
        Optional<Character> taskOptional = characterService.getCharacterById(id);
        if (taskOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(taskOptional.get());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id, @RequestBody Character character) {
        Optional<Character> characterOptional = characterService.updateCharacter(id, character);
        if (characterOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(characterOptional.get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Character> deleteCharacter(@PathVariable Long id) {
        Optional<Character> characterOptional = characterService.deleteCharacter(id);
        if (characterOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(characterOptional.get());
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<Character>> findByName(@RequestParam String name) {
        return ResponseEntity.ok().body(characterService.getCharactersByName(name));
    }

    @GetMapping("/findByRace")
    public ResponseEntity<List<Character>> findByRace(@RequestParam RaceEnum raceEnum) {
        return ResponseEntity.ok().body(characterService.getCharactersByRace(raceEnum));
    }

    @PutMapping("/fight/physical/{defenderId}")
    public ResponseEntity<String> characterPhysicFight(@RequestBody Character attackCharacter, @PathVariable Long defenderId) {
        return ResponseEntity.ok().body(characterService.characterPhysicFight(attackCharacter, defenderId));
    }

    @PutMapping("/fight/magical/{defenderId}")
    public ResponseEntity<String> characterMagicalFight(@RequestBody Character attackCharacter, @PathVariable Long defenderId, SpecialMovesEnum specialAttack) throws Exception {
        return ResponseEntity.ok().body(characterService.characterMagicFight(attackCharacter, defenderId, specialAttack));
    }
}

