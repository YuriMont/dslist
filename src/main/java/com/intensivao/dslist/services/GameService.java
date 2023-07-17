package com.intensivao.dslist.services;

import com.intensivao.dslist.dto.GameDTO;
import com.intensivao.dslist.dto.GameMinDTO;
import com.intensivao.dslist.entities.Game;
import com.intensivao.dslist.exceptions.GameTitleAlreadyExitsException;
import com.intensivao.dslist.exceptions.NotFoundIdException;
import com.intensivao.dslist.projections.GameMinProjection;
import com.intensivao.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(){
       List<Game> result = gameRepository.findAll();

       return result.stream().map(x -> new GameMinDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public void createGame(Game game){
       boolean gameExists = gameRepository.findByTitle(game.getTitle()).isPresent();

       if(gameExists){
            throw new GameTitleAlreadyExitsException("O titulo do game já está em uso");
       }

       gameRepository.save(game);
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id){
        Game result = gameRepository.findById(id).orElseThrow(() -> new NotFoundIdException("Id not found"));

        return new GameDTO(result);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId){
        List<GameMinProjection> result = gameRepository.searchByList(listId);

        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }
}
