package com.intensivao.dslist.services;

import com.intensivao.dslist.dto.GameListDTO;
import com.intensivao.dslist.entities.GameList;
import com.intensivao.dslist.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    GameListRepository gameListRepository;

    public List<GameListDTO> findAll(){
        List<GameList> result = gameListRepository.findAll();

        return result.stream().map(x -> new GameListDTO(x)).toList();
    }
}
