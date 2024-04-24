package com.develhope.springFantasy;

import com.develhope.springFantasy.controllers.CharacterController;
import com.develhope.springFantasy.entities.Character;
import com.develhope.springFantasy.enums.CharacterClassEnum;
import com.develhope.springFantasy.enums.RaceEnum;
import com.develhope.springFantasy.repositories.CharacterRepository;
import com.develhope.springFantasy.services.CharacterService;
import com.develhope.springFantasy.services.FightUtilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpringFantasyTest {

    private static final Long idTest = 1L;
    @Autowired
    private CharacterController characterController;

    @Autowired
    private FightUtilities fightUtilities;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper1;


    @Test
    void contextLoad() {
    }

    // test per creare un nuovo character
    @Test
    @Order(1)
    void createCharacter() throws Exception {
        Character character = new Character();
        character.setId(idTest);
        character.setName("Opippo");
        character.setLifePoints(2000);
        character.setLevel(1);
        character.setManaPoints(50);
        character.setRace(RaceEnum.ELF);
        character.setDescription("lillo");
        character.setPhysicalStrength(30);
        character.setCharacterClassEnum(CharacterClassEnum.WIZARD);

        String characterJSON = objectMapper1.writeValueAsString(character);
        MvcResult resultActions = this.mockMvc.perform(post("/character/create")
                .contentType(MediaType.APPLICATION_JSON).content(characterJSON))
                .andDo(print()).andReturn();

    }

    @Test
    @Order(3)
    void getAllCharacters() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/character/all"))
                .andDo(print()).andReturn();

        List<Character> userFromResponseList = objectMapper1.readValue(result.getResponse().getContentAsString(),
                List.class);
        assertThat(userFromResponseList.size()).isNotZero();
    }

    // test per cercare un character tramite id
    @Test
    @Order(4)
    void getCharactersId() throws Exception {
        this.createCharacter();

        MvcResult resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/character/single/{id}", idTest))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(idTest)).andReturn();
    }

    // test per aggiornare un character tramite id
    @Test
    @Order(2)
    void updateCharacterId() throws Exception {
        Long idTest2 = idTest;
        Character updateCharacter = new Character();
        updateCharacter.setName("Pippo2");
        String characterJSON = objectMapper1.writeValueAsString(updateCharacter);

        MvcResult resultUpdate = this.mockMvc.perform(MockMvcRequestBuilders.put("/character/edit/{id}", idTest2)
                        .contentType(MediaType.APPLICATION_JSON).content(characterJSON))
                .andDo(print()).andExpect(status().isOk()).andReturn();

        String content = resultUpdate.getResponse().getContentAsString();
        Assertions.assertNotNull(content);

    }

    // test per eliminare un character tramite id
    @Test

    @Order(5)
    void deleteCharacter() throws Exception {
        Long idTest2 = idTest;

        MvcResult result = mockMvc.perform(delete("/character/delete/{id}", idTest2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }


}
