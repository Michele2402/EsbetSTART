package unisa.esbetstart.eventmanagement.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class Competition {

    private UUID id;
    private String name;
    private String originCountry;
    private Game game;

    @Builder
    public Competition(UUID id, String name, String originCountry, Game game) {
        this.id = id;
        this.name = name;
        this.originCountry = originCountry;
        this.game = game;
    }

    //TODO da completare con i metodi
}
