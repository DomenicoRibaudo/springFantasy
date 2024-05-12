package com.develhope.springFantasy.controllers;

import com.develhope.springFantasy.entities.Character;
import com.develhope.springFantasy.enums.FightEnum;
import com.develhope.springFantasy.enums.RaceEnum;
import com.develhope.springFantasy.enums.SpecialMovesEnum;
import com.develhope.springFantasy.services.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "This API creates a Character on the DB. And returns the object created for confirmation")
    public ResponseEntity<Character> create(@RequestBody Character character) {
        return ResponseEntity.ok().body(characterService.createCharacter(character));
    }

    @GetMapping("/all")
    @Operation(summary = "This API retrives a List with all the Characters in the DB.")
    public ResponseEntity<List<Character>> showCharacters() {
        return ResponseEntity.ok().body(characterService.getAllCharacters());
    }

    @GetMapping("/single/{id}")
    @Operation(summary = "This API retrives a single Character from the DB.")
    public ResponseEntity<Character> showSingleCharacter(@PathVariable Long id) {

        Optional<Character> taskOptional = characterService.getCharacterById(id);
        if (taskOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(taskOptional.get());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "This API updates a Character on the DB. And returns the object updated for confirmation")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id, @RequestBody Character character) {
        Optional<Character> characterOptional = characterService.updateCharacter(id, character);
        if (characterOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(characterOptional.get());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "This API deletes a Character from the DB. And returns the object deleted for confirmation")
    public ResponseEntity<Character> deleteCharacter(@PathVariable Long id) {
        Optional<Character> characterOptional = characterService.deleteCharacter(id);
        if (characterOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(characterOptional.get());
    }

    @GetMapping("/findByName")
    @Operation(summary = "This API retrives a character by Name.")
    public ResponseEntity<List<Character>> findByName(@RequestParam String name) {
        return ResponseEntity.ok().body(characterService.getCharactersByName(name));
    }

    @GetMapping("/findByRace")
    @Operation(summary = "This API retrives a character by Race.")
    public ResponseEntity<List<Character>> findByRace(@RequestParam RaceEnum raceEnum) {
        return ResponseEntity.ok().body(characterService.getCharactersByRace(raceEnum));
    }

    @PutMapping ("/fight/{defenderId}/{attackerId}")
    @Operation(summary = "This API is the general method to make 2 characters fight.")
    public ResponseEntity<String> characterPhysicFight(@PathVariable Long defenderId, @PathVariable Long attackerId, @RequestParam FightEnum fight, @RequestParam(required = false) SpecialMovesEnum specialMoves) throws Exception {
        return ResponseEntity.ok().body(characterService.characterFight(defenderId, attackerId, fight, specialMoves));
    }
}
