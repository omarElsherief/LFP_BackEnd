package com.zanta.lfp.repository;

import com.zanta.lfp.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
    Game findByName(String name);
    boolean existsByName(String name);
    void deleteByName(String name);
}
