package com.intensivao.dslist.services_tests;

import com.intensivao.dslist.entities.Belonging;
import com.intensivao.dslist.entities.Game;
import com.intensivao.dslist.entities.GameList;
import com.intensivao.dslist.repositories.GameListRepository;
import com.intensivao.dslist.repositories.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

public abstract class BaseTest {
    @MockBean
    GameRepository gameRepository;

    @MockBean
    GameListRepository gameListRepository;

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

        Mockito.when(gameRepository.findByTitle(Mockito.anyString())).thenAnswer(item -> {
            String title = item.getArgument(0);
            Optional<Game> foundGame = games.stream().filter(game -> game.getTitle().equals(title)).findFirst();
            return foundGame;
        });

        Mockito.when(gameRepository.findAll()).thenReturn(games);

        //Mock about GameList

        GameList gameListEntity1 = new GameList(1L, "Aventura e RPG");
        GameList gameListEntity2 = new GameList(2L, "Jogos de plataforma");
        GameList gameListEntity3 = new GameList(3L, "Jogos de esporte");

        List<GameList> gameLists = List.of(gameListEntity1, gameListEntity2, gameListEntity3);
        Mockito.when(gameListRepository.findAll()).thenReturn(gameLists);

        Mockito.when(gameListRepository.findByName(Mockito.anyString())).thenAnswer(item -> {
           String name = item.getArgument(0);
           Optional<GameList> foundGameList = gameLists.stream().filter(
                   gameList -> gameList.getName().equals(name)
           ).findFirst();
           return foundGameList;
        });

        //Mock about Belonging

        Belonging belongingEntity1 = new Belonging(gameEntity1, gameListEntity1, 0);
        Belonging belongingEntity2 = new Belonging(gameEntity2, gameListEntity1, 1);
        Belonging belongingEntity3 = new Belonging(gameEntity3, gameListEntity1, 2);
        Belonging belongingEntity4 = new Belonging(gameEntity4, gameListEntity1, 3);

        Belonging belongingEntity5 = new Belonging(gameEntity5, gameListEntity2, 0);
        Belonging belongingEntity6 = new Belonging(gameEntity6, gameListEntity2, 1);
        Belonging belongingEntity7 = new Belonging(gameEntity7, gameListEntity2, 2);
        Belonging belongingEntity8 = new Belonging(gameEntity8, gameListEntity2, 3);

        Belonging belongingEntity9 = new Belonging(gameEntity9, gameListEntity3, 0);
        Belonging belongingEntity10 = new Belonging(gameEntity10, gameListEntity3, 1);
        Belonging belongingEntity11 = new Belonging(gameEntity11, gameListEntity3, 2);
        Belonging belongingEntity12 = new Belonging(gameEntity12, gameListEntity3, 3);

        List<Belonging> belongings = List.of(
                belongingEntity1, belongingEntity2, belongingEntity3, belongingEntity4,
                belongingEntity5, belongingEntity6, belongingEntity7, belongingEntity8,
                belongingEntity9, belongingEntity10, belongingEntity11, belongingEntity12
        );

        Mockito.when(gameRepository.searchByList(Mockito.anyLong())).thenAnswer(item -> {
            Long listId = item.getArgument(0);
            return belongings.stream().filter(
                    belonging -> belonging.getId().getGameList().getId().equals(listId))
                    .toList();
        });




    }
}
