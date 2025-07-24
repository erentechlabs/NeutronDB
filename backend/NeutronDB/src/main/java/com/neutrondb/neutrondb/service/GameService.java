package com.neutrondb.neutrondb.service;

import com.neutrondb.neutrondb.domain.entities.GameEntity;
import com.neutrondb.neutrondb.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    public List<GameEntity> getGames() {
        return gameRepository.findAll();
    }

    public GameEntity getGameById(int gameId) {
        return gameRepository.findById(gameId).orElse(new GameEntity());

    }

    public void addGame(GameEntity gameEntity) {
        gameRepository.save(gameEntity);
    }

    public void updateGame(GameEntity gameEntity) {
        gameRepository.save(gameEntity);
    }

    public void deleteGame(GameEntity gameEntity) {
        gameRepository.delete(gameEntity);
    }

    public void deleteAllGames() {
        gameRepository.deleteAll();
    }
}
