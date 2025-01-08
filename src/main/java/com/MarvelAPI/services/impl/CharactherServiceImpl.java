package com.MarvelAPI.services.impl;

import com.MarvelAPI.persistence.integration.Marvel.Dto.CharacterDto;
import com.MarvelAPI.persistence.integration.Marvel.repository.CharacterRepository;
import com.MarvelAPI.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharactherServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    public List<CharacterDto> findAll(int[] series, int[] comic, int limit, String name) {

        return characterRepository.findAll(series,comic,limit,name);
    }
}
