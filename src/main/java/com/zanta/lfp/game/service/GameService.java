package com.zanta.lfp.game.service;


import com.zanta.lfp.game.model.Game;
import com.zanta.lfp.game.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public ResponseEntity<?> addGame(Game game) {
        var savedGame = gameRepository.save(game);
        return ResponseEntity
                .ok(Map.of("message", "Game added successfully", "game", savedGame));
    }

    public ResponseEntity<?> getAllGames() {
        var games = gameRepository.findAll();
        return ResponseEntity.ok(Map.of("games", games));
    }

    public ResponseEntity<?> getGame(String name) {
        if (!gameRepository.existsByName(name)) {
            return ResponseEntity.notFound().build();
        }
        var game = gameRepository.findByName(name);
        return ResponseEntity.ok(Map.of("game", game));
    }

    public ResponseEntity<?> deleteGame(String name) {
        if (!gameRepository.existsByName(name)) {
            return ResponseEntity.notFound().build();
        }
        gameRepository.deleteByName(name);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }
}
