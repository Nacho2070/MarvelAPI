package com.MarvelAPI.web.controller;

import com.MarvelAPI.persistence.integration.Marvel.Dto.CharacterDto;
import com.MarvelAPI.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/findAll")
    public ResponseEntity<List<CharacterDto>> findAll(
            @RequestParam(required = true) int[] series,
            @RequestParam(required = false) int[] comic,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "") String name
    )
    {
        return ResponseEntity.ok(characterService.findAll(series,comic,limit,name));
    }

}
