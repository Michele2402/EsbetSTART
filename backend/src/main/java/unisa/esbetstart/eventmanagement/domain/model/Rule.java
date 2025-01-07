package unisa.esbetstart.eventmanagement.domain.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Data
@Slf4j
public class Rule {

    private String name;
    private Integer position;
    private Game game;

    @Builder
    public Rule(String name, Integer position, Game game) {
        this.name = name;
        this.position = position;
        this.game = game;
    }


}
