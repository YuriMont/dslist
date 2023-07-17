package com.intensivao.dslist.services_tests;

import com.intensivao.dslist.dto.GameDTO;
import com.intensivao.dslist.entities.Game;
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

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class GameServiceTest {

    @TestConfiguration
    static class GameServiceConfiguration{
        @Bean
        public GameService gameService(){
            return new GameService();
        }
    }

    @MockBean
    GameRepository gameRepository;

    @Autowired
    GameService gameService;

    @Test
    public void itShouldBeAbleFindAGameById(){
        Assertions.assertInstanceOf(GameDTO.class, gameService.findById(1L));
    }

    @Test
    public void itShouldNotBeAbleFindAGameId(){
        Assertions.assertThrows(NotFoundIdException.class, () -> gameService.findById(90L));
    }

    @Test
    public void itShouldBeAbleFindAllGames(){
        Assertions.assertNotNull(gameService.findAll());
    }


    @BeforeEach
    public void setup(){
        Game gameEntity1 = new Game(1L, "Sonic", 1990, "Arcade", "PC, Playstation", 4.3, "link", "Teste de descrição curta", "Teste de descrição longa");
        Game gameEntity2 = new Game(2L, "Mario", 1991, "Arcade", "PC, Playstation", 4.3, "link", "Teste de descrição curta", "Teste de descrição longa");
        Game gameEntity3 = new Game(3L, "The Legend of Zelda", 1986, "Nintendo Entertainment System", "Switch", 4.8, "link", "Teste de descrição curta", "Teste de descrição longa");
        Game gameEntity4 = new Game(4L, "Final Fantasy VII", 1997, "PlayStation", "PlayStation 4, PC", 4.9, "link", "Teste de descrição curta", "Teste de descrição longa");
        Game gameEntity5 = new Game(5L, "Minecraft", 2011, "PC", "PlayStation, Xbox, Nintendo Switch", 4.7, "link", "Teste de descrição curta", "Teste de descrição longa");
        Game gameEntity6 = new Game(6L, "Grand Theft Auto V", 2013, "PlayStation 3, Xbox 360", "PlayStation 4, Xbox One, PC", 4.6, "link", "Teste de descrição curta", "Teste de descrição longa");
        Game gameEntity7 = new Game(7L, "The Witcher 3: Wild Hunt", 2015, "PC, PlayStation 4, Xbox One", "Nintendo Switch", 4.8, "link", "Teste de descrição curta", "Teste de descrição longa");
        Game gameEntity8 = new Game(8L, "Overwatch", 2016, "PC, PlayStation 4, Xbox One", "Nintendo Switch", 4.5, "link", "Teste de descrição curta", "Teste de descrição longa");
        Game gameEntity9 = new Game(9L, "Fortnite", 2017, "PC, PlayStation 4, Xbox One", "Nintendo Switch, Mobile", 4.4, "link", "Teste de descrição curta", "Teste de descrição longa");
        Game gameEntity10 = new Game(10L, "Red Dead Redemption 2", 2018, "PlayStation 4, Xbox One", "PC", 4.9, "link", "Teste de descrição curta", "Teste de descrição longa");
        Game gameEntity11 = new Game(11L, "Cyberpunk 2077", 2020, "PC, PlayStation 4, Xbox One", "PlayStation 5, Xbox Series X/S", 3.9, "link", "Teste de descrição curta", "Teste de descrição longa");
        Game gameEntity12 = new Game(12L, "Animal Crossing: New Horizons", 2020, "Nintendo Switch", "", 4.7, "link", "Teste de descrição curta", "Teste de descrição longa");

        List<Game> games = List.of(
                gameEntity1, gameEntity2, gameEntity3,
                gameEntity4, gameEntity5, gameEntity6,
                gameEntity7, gameEntity8, gameEntity9,
                gameEntity10, gameEntity11, gameEntity12
        );

        Mockito.when(gameRepository.findById(Mockito.anyLong())).thenAnswer(item -> {
            Long id = item.getArgument(0);
            Optional<Game> foundGame = games.stream()
                    .filter(game -> game.getId().equals(id))
                    .findFirst();
            return foundGame;
        });

        Mockito.when(gameRepository.findAll()).thenReturn(games);
    }

}
