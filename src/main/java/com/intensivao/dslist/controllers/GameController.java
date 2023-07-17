package com.intensivao.dslist.controllers;

import com.intensivao.dslist.dto.GameMinDTO;
import com.intensivao.dslist.dto.ResponseDTO;
import com.intensivao.dslist.entities.Game;
import com.intensivao.dslist.exceptions.GameTitleAlreadyExitsException;
import com.intensivao.dslist.exceptions.NotFoundIdException;
import com.intensivao.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameMinDTO> findAll(){
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.status(200).body(gameService.findById(id));
        }catch (NotFoundIdException e){
            return ResponseEntity.status(400).body(new ResponseDTO(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody Game game){
        try{
            gameService.createGame(game);
            return ResponseEntity.status(201).body(new ResponseDTO("Game inserido com sucesso!"));
        }catch (GameTitleAlreadyExitsException e){
            return ResponseEntity.status(404).body(new ResponseDTO(e.getMessage()));
        }
    }
}
