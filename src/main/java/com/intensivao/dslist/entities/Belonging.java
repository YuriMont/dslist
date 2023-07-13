package com.intensivao.dslist.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tb_belonging")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Belonging {

    @EmbeddedId
    private BelongingPK id = new BelongingPK();
    private Integer position;

    public Belonging(Game game, GameList list, Integer position) {
        id.setGame(game);
        id.setGameList(list);
        this.position = position;
    }


}
