package unisa.esbetstart.eventmanagement.domain.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.DomainAttributeException;
import unisa.esbetstart.slipmanagment.domain.model.OddStatic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static unisa.esbetstart.slipmanagment.domain.enums.ResultEnum.*;

@NoArgsConstructor
@Data
@Slf4j
public class Odd {

    private UUID id;
    private String name;
    private int position;
    private double value;
    private Event event;
    private Set<OddStatic> oddStatics;

    @Builder
    public Odd(UUID id, String name, double value, Event event, int position, Set<OddStatic> oddStatics) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.event = event;
        this.position = position;
        this.oddStatics = oddStatics;

    }


    /**
     * This method evaluates the Odd
     *
     * @param isWon true if the Odd is won, false otherwise
     * @return the list of OddStatic ids
     */
    public List<UUID> evaluate(boolean isWon) {

        List<UUID> oddStaticList = new ArrayList<>();

        if(isWon) {
            log.info("Odd with id {} is won", id);
            oddStatics.forEach(oddStatic -> {
                oddStatic.setResult(WON);
                oddStaticList.add(oddStatic.getId());});
        } else {
            log.info("Odd with id {} is lost", id);
            oddStatics.forEach(oddStatic -> {
                oddStatic.setResult(LOST);
                oddStaticList.add(oddStatic.getId());});
        }

        return oddStaticList;

    }

    /**
     * This method updates the Odd value
     * @param value the new value
     */
    public void updateOdd(double value) {

        if(value < 0) {
            log.error("Odd value cannot be negative");
            throw new DomainAttributeException("Odd value cannot be negative");
        }

        this.value = value;
    }

    /**
     * This method maps the Odd to an OddStatic
     * @return the OddStatic
     */
    public OddStatic toOddStatic() {
        return OddStatic.builder()
                .id(UUID.randomUUID())
                .competition(event.getCompetition().getName())
                .game(event.getCompetition().getGame().getName())
                .result(PLAYING)
                .name(name)
                .value(value)
                .odd(Odd.builder()
                        .id(id)
                        .build())
                .build();
    }

}
