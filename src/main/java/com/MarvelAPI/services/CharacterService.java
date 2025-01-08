package com.MarvelAPI.services;

import com.MarvelAPI.persistence.integration.Marvel.Dto.CharacterDto;

import java.util.List;

public interface CharacterService {

    List<CharacterDto> findAll(int[] series, int[] comic, int limit, String name);
}
