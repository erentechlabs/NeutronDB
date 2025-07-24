package com.neutrondb.neutrondb.repository;

import com.neutrondb.neutrondb.domain.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Integer> {

}
