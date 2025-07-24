package com.neutrondb.neutrondb.controller;

import com.neutrondb.neutrondb.domain.dto.GameDTO;
import com.neutrondb.neutrondb.domain.entities.GameEntity;
import com.neutrondb.neutrondb.mapper.Mapper;
import com.neutrondb.neutrondb.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/games")
@RestController
public class GameController {

    private final GameService gameService;

    private final Mapper<GameEntity, GameDTO> gameMapper;

    public GameController(GameService gameService, Mapper<GameEntity, GameDTO> gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }


    @GetMapping
    public List<GameDTO> getGames() {
        List<GameEntity> games = gameService.getGames();
        return games.stream()
                .map(gameMapper::mapTo)
                .collect(Collectors.toList());
    }


    @GetMapping("/{gameId}")
    public GameDTO getGameById(@PathVariable int gameId) {
        GameEntity gameEntity = gameService.getGameById(gameId);
        return gameMapper.mapTo(gameEntity);
    }


    @PostMapping
    public GameDTO addGame(@RequestBody GameDTO gameDTO) {
        GameEntity gameEntity = gameMapper.mapFrom(gameDTO);
        gameService.addGame(gameEntity);

        return  gameMapper.mapTo(gameEntity);
    }


    @PutMapping("/{gameId}")
    public GameDTO updateGame(@PathVariable int gameId, @RequestBody GameDTO gameDTO) {

        GameEntity gameEntity = gameMapper.mapFrom(gameDTO);
        gameService.updateGame(gameEntity);
        return gameMapper.mapTo(gameEntity);
    }


    @DeleteMapping("/{gameId}")
    public void deleteGame(@PathVariable int gameId) {
        GameEntity gameEntity = gameService.getGameById(gameId);
        gameService.deleteGame(gameEntity);
    }


    @DeleteMapping
    public void deleteAllGames() {
        gameService.deleteAllGames();
    }
}