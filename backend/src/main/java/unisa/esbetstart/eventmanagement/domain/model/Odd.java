package unisa.esbetstart.eventmanagement.domain.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.slipmanagment.domain.model.OddStatic;

import java.util.Set;
import java.util.UUID;

import static unisa.esbetstart.slipmanagment.domain.enums.ResultEnum.*;

@NoArgsConstructor
@Data
@Slf4j
public class Odd {

    private UUID id;
    private String name;
    private double value;
    private Event event;
    private Set<OddStatic> oddStatics;

    @Builder
    public Odd(UUID id, String name, double value, Event event) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.event = event;
    }


    /**
     * This method evaluates the Odd
     * @param isWon true if the Odd is won, false otherwise
     */
    public void evaluate(boolean isWon) {

        if(isWon) {
            log.info("Odd with id {} is won", id);
            oddStatics.forEach(oddStatic -> oddStatic.setResult(WON));
        } else {
            log.info("Odd with id {} is lost", id);
            oddStatics.forEach(oddStatic -> oddStatic.setResult(LOST));

        }
    }

    /**
     * This method maps the Odd to an OddStatic
     * @return the OddStatic
     */
    public OddStatic toOddStatic() {
        return OddStatic.builder()
                .id(UUID.randomUUID())
                .date(event.getDate())
                .competition(event.getCompetition().getName())
                .game(event.getCompetition().getGame().getName())
                .result(PLAYING)
                .name(name)
                .value(value)
                .build();
    }

}
