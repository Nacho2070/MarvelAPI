package com.MarvelAPI.persistence.integration.Marvel.mapper;

import com.MarvelAPI.persistence.integration.Marvel.Dto.CharacterDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

public class CharacterMapper {

    public static List<CharacterDto> toDtoList(JsonNode response) {
        ArrayNode resultsNode = getResultNode(response);
        List<CharacterDto> characterDto = new ArrayList<>();
        resultsNode.elements().forEachRemaining(each -> {
            characterDto.add(CharacterMapper.toDto(each));
        });

        return characterDto;
    }

    private static ArrayNode getResultNode(JsonNode response) {
        if (response == null){
            throw new RuntimeException("response is null");
        }
        JsonNode resultsNode = response.get("data");
        return (ArrayNode) resultsNode.get("results");
    }

    private static CharacterDto toDto(JsonNode characterNode){

        CharacterDto characterDto = new CharacterDto(
                characterNode.get("id").asInt(),
                characterNode.get("name").asText(),
                characterNode.get("description").asText(),
                characterNode.get("modified").asText(),
                characterNode.get("resourceURI").asText()
        );
        return characterDto;
    }
}
