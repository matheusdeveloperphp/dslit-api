package com.solucaotecnologia.dslist.services;

import com.solucaotecnologia.dslist.dto.GameDTO;
import com.solucaotecnologia.dslist.dto.GaminDTO;
import com.solucaotecnologia.dslist.entities.Game;
import com.solucaotecnologia.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GaminDTO> findAll() {
        List<Game> game = gameRepository.findAll();
        return game.stream().map(x -> new GaminDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        Game result = gameRepository.findById(id).get();
        return new GameDTO(result);
    }
}
