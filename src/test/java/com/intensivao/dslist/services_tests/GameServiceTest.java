package com.intensivao.dslist.services_tests;

import com.intensivao.dslist.dto.GameDTO;
import com.intensivao.dslist.entities.Game;
import com.intensivao.dslist.exceptions.GameTitleAlreadyExitsException;
import com.intensivao.dslist.exceptions.NotFoundIdException;
import com.intensivao.dslist.repositories.GameRepository;
import com.intensivao.dslist.services.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GameServiceTest extends BaseTest{

    @TestConfiguration
    static class GameServiceConfiguration{
        @Bean
        public GameService gameService(){
            return new GameService();
        }
    }

    @Autowired
    GameService gameService;

    @Test
    public void itShouldBeAbleFindAGameById(){
        Assertions.assertInstanceOf(GameDTO.class, gameService.findById(1L));
        Assertions.assertInstanceOf(GameDTO.class, gameService.findById(2L));
        Assertions.assertInstanceOf(GameDTO.class, gameService.findById(3L));
        Assertions.assertInstanceOf(GameDTO.class, gameService.findById(4L));
        Assertions.assertInstanceOf(GameDTO.class, gameService.findById(5L));
        Assertions.assertInstanceOf(GameDTO.class, gameService.findById(6L));
    }

    @Test
    public void itShouldNotBeAbleFindAGameId(){
        Assertions.assertThrows(NotFoundIdException.class, () -> gameService.findById(90L));
    }

    @Test
    public void itShouldBeAbleCreateGame(){
        Assertions.assertDoesNotThrow(() -> gameService.createGame(new Game(13L, "FIFA", 2016, "PS", "PC, Playstation", 4.3, "link", "Teste de descrição curta", "Teste de descrição longa")));
    }

    @Test
    public void itShouldNotBeAbleCreateGame(){
        Assertions.assertThrows(GameTitleAlreadyExitsException.class, () -> gameService.createGame(new Game(13L, "Sonic", 2016, "PS", "PC, Playstation", 4.3, "link", "Teste de descrição curta", "Teste de descrição longa")));
    }

    @Test
    public void itShouldBeAbleFindAllGames(){
        Assertions.assertNotNull(gameService.findAll());
    }

}
