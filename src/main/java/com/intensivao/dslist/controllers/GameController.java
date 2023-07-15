package com.intensivao.dslist.controllers;

import com.intensivao.dslist.dto.GameMinDTO;
import com.intensivao.dslist.dto.ResponseErrorDTO;
import com.intensivao.dslist.exceptions.NotFoundIdException;
import com.intensivao.dslist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return ResponseEntity.status(400).body(new ResponseErrorDTO(e.getMessage()));
        }

    }
}
