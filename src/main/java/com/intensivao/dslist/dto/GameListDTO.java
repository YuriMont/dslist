package com.intensivao.dslist.dto;

import com.intensivao.dslist.entities.GameList;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GameListDTO {
    private Long id;
    private String name;

    public GameListDTO(GameList gameList){
        id = gameList.getId();
        name = gameList.getName();
    }

}
