package com.MarvelAPI.persistence.integration.Marvel.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public record CharacterDto(
      int id,
      String name,
      String description,
      String modified,
      String resourceURI
){
    public static record CharacterInfo(
            String imagePath,
            String description
    ){}
}
