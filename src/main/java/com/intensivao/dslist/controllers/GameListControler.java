package com.intensivao.dslist.controllers;

import com.intensivao.dslist.dto.GameListDTO;
import com.intensivao.dslist.dto.GameMinDTO;
import com.intensivao.dslist.dto.ReplacementDTO;
import com.intensivao.dslist.dto.ResponseDTO;
import com.intensivao.dslist.entities.GameList;
import com.intensivao.dslist.exceptions.GameListNameAlreadyExitsException;
import com.intensivao.dslist.services.GameListService;
import com.intensivao.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lists")
public class GameListControler {

    @Autowired
    private GameListService gameListService;

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameListDTO> findAll(){
        return gameListService.findAll();
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(GameList gameList){
        try{
            gameListService.createListGame(gameList);
            return ResponseEntity.status(201).body(new ResponseDTO("Lista de jogos adicionado com sucesso!"));
        }catch (GameListNameAlreadyExitsException e){
            return ResponseEntity.status(404).body(new ResponseDTO(e.getMessage()));
        }

    }

    @GetMapping("/{listId}/games")
    public List<GameMinDTO> findByList(@PathVariable Long listId){
        return gameService.findByList(listId);
    }

    @PutMapping("/{listId}/replacement")
    public void move(@PathVariable Long listId, @RequestBody ReplacementDTO replacementDTO){
        gameListService.move(listId, replacementDTO.getSourceIndex(), replacementDTO.getDestinationIndex());
    }
}
