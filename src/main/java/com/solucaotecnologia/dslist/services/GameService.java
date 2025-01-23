package com.solucaotecnologia.dslist.services;

import com.solucaotecnologia.dslist.dto.GaminDTO;
import com.solucaotecnologia.dslist.entities.Game;
import com.solucaotecnologia.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GaminDTO> findAll() {
        List<Game> game = gameRepository.findAll();
        return game.stream().map(x -> new GaminDTO(x)).toList();
    }
}
