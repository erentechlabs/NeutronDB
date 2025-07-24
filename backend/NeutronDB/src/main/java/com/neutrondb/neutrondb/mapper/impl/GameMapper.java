package com.neutrondb.neutrondb.mapper.impl;

import com.neutrondb.neutrondb.domain.dto.GameDTO;
import com.neutrondb.neutrondb.domain.entities.GameEntity;
import com.neutrondb.neutrondb.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GameMapper implements Mapper<GameEntity, GameDTO> {

    private final ModelMapper modelMapper;

    public GameMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GameDTO mapTo(GameEntity gameEntity) {
        return modelMapper.map(gameEntity, GameDTO.class);
    }

    @Override
    public GameEntity mapFrom(GameDTO gameDTO) {
        return modelMapper.map(gameDTO, GameEntity.class);

    }
}
