package unisa.esbetstart.eventmanagement.domain.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import unisa.esbetstart.common.exceptions.DomainAttributeException;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class Event {

    private UUID id;
    private String name;
    private LocalDateTime date;
    private Competition competition;
    private Set<Odd> odds;

    @Builder
    public Event(UUID id, String name, LocalDateTime date, Competition competition, Set<Odd> odds) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.competition = competition;
        this.odds = odds;
    }

    public void addOdd(Odd odd) {

        if(odds.contains(odd)) {
            log.error("Odd {} already exists in {}", odd.getName(), name);
            throw new DomainAttributeException("Odd " + odd.getName() + " already exists in " + name);
        }

        odds.add(odd);
    }


    public void updateEvent(String name, LocalDateTime date, Set<Odd> odds) {

        if(name == null || date == null || competition == null || odds == null) {
            log.error("Event name, date, competition or odds cannot be null");
            throw new DomainAttributeException("Event name, date, competition or odds cannot be null");
        }

        if(name.length() > 30) {
            log.error("Event name cannot be longer than 30 characters");
            throw new DomainAttributeException("Event name cannot be longer than 30 characters");
        }

        this.name = name;
        this.date = date;
        this.competition = competition;
        this.odds = odds;
    }

}
