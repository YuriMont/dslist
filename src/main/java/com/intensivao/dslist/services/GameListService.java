package com.intensivao.dslist.services;

import com.intensivao.dslist.dto.GameListDTO;
import com.intensivao.dslist.entities.GameList;
import com.intensivao.dslist.exceptions.GameListNameAlreadyExitsException;
import com.intensivao.dslist.projections.GameMinProjection;
import com.intensivao.dslist.repositories.GameListRepository;
import com.intensivao.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    GameListRepository gameListRepository;

    @Autowired
    GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameList> result = gameListRepository.findAll();

        return result.stream().map(x -> new GameListDTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public void createListGame(GameList gameList){
        boolean gameListExists = gameListRepository.findByName(gameList.getName()).isPresent();

        if(gameListExists){
            throw new GameListNameAlreadyExitsException("O nome da lista de jogos já está em uso");
        }

        gameListRepository.save(gameList);
    }

    @Transactional(readOnly = true)
    public void move(Long listId, int sourceIndex, int destinationIndex){
        List<GameMinProjection> list = gameRepository.searchByList(listId);

        GameMinProjection obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);

        int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
        int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

        for(int i = min; i <= max; i++){
            gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }
    }

}
