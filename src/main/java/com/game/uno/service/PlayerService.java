package com.game.uno.service;

import com.game.uno.dao.PlayerRepository;
import com.game.uno.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public void savePlayer(String name, int score, String date) {
        Player p = new Player();
        p.setName(name);
        p.setScore(score);
        p.setDate(date);

        repository.save(p);
    }

    public List<Player> getTopPlayers() {
        return repository.findTop3ByOrderByScoreDesc();
    }
}
