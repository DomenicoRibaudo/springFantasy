package com.develhope.springFantasy.repositories;

import com.develhope.springFantasy.entities.Character;
import com.develhope.springFantasy.enums.RaceEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long>  {
    @Query(value = "SELECT * FROM characters as c WHERE c.name = :inputValue", nativeQuery = true)
    List<Character> findCharacterByName(@Param("inputValue") String inputValue);

    @Query(value = "SELECT * FROM characters as c WHERE c.race = :inputValue", nativeQuery = true)
    List<Character> findCharacterByRace(@Param("inputValue") RaceEnum inputValue);




}
