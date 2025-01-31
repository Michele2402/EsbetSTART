package unisa.esbetstart.slipmanagment.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.eventmanagement.domain.model.Odd;
import unisa.esbetstart.slipmanagment.domain.enums.ResultEnum;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class OddStatic {

    private UUID id;
    private LocalDateTime date;
    private String competition;
    private String game;
    private ResultEnum result;
    private String name;
    private double value;
    private BetPlaced betPlaced;
    private Odd odd;

    @Builder
    public OddStatic(UUID id, LocalDateTime date, String competition, String game, ResultEnum result, String name, double value, BetPlaced betPlaced, Odd odd) {

        this.id = id;
        this.date = date;
        this.competition = competition;
        this.game = game;
        this.result = result;
        this.name = name;
        this.value = value;
        this.betPlaced = betPlaced;
        this.odd = odd;
    }

}
