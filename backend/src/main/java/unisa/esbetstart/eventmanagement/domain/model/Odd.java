package unisa.esbetstart.eventmanagement.domain.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class Odd {

    private UUID id;
    private String name;
    private double value;
    private Event event;

    @Builder
    public Odd(UUID id, String name, double value, Event event) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.event = event;
    }

}
