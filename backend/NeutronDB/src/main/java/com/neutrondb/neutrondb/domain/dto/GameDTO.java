package com.neutrondb.neutrondb.domain.dto;

import com.neutrondb.neutrondb.domain.enums.Platform;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDTO {
    private Integer id;
    private String name;
    private String developer;
    private String genre;
    private Set<Platform> platforms;
    private Boolean deckVerifiedStatus;
}