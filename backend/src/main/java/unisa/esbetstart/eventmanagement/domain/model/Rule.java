package unisa.esbetstart.eventmanagement.domain.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class Rule {

    private UUID id;
    private String name;
    private Integer position;
    private Game game;

    @Builder
    public Rule(String name, Integer position, Game game, UUID id) {

        this.id = id;
        this.name = name;
        this.position = position;
        this.game = game;

    }


}
