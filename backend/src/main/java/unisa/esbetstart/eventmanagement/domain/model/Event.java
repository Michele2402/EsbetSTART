package unisa.esbetstart.eventmanagement.domain.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.DomainAttributeException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class Event implements Searchable {

    private UUID id;
    private String name;
    private LocalDateTime date;
    private boolean isEnded = false;
    private Competition competition;
    private Set<Odd> odds;

    @Builder
    public Event(UUID id, String name, LocalDateTime date, Competition competition, Set<Odd> odds, boolean isEnded) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.competition = competition;
        this.odds = odds;
        this.isEnded = isEnded;
    }

    /**
     * This method updates the event name, date, competition and odds
     *
     * @param name the new name of the event
     * @param date the new date of the event
     * @throws DomainAttributeException if the name, date or odds are null
     * @throws DomainAttributeException if the name is longer than 30 characters
     */
    public void updateEvent(String name, LocalDateTime date) {

        if (name == null || date == null) {
            log.error("Event name, date, competition or odds cannot be null");
            throw new DomainAttributeException("Event name, date, competition or odds cannot be null");
        }

        if (name.length() > 30) {
            log.error("Event name cannot be longer than 30 characters");
            throw new DomainAttributeException("Event name cannot be longer than 30 characters");
        }

        this.name = name;
        this.date = date;
    }

    /**
     * This method adds an odd to the event
     *
     * @param odd the odd to add
     * @throws DomainAttributeException if the odd already exists in the event
     * @throws DomainAttributeException if the odd is null
     */
    public void addOdd(Odd odd) {

        //null check
        if (odd == null) {
            log.error("Odd cannot be null");
            throw new DomainAttributeException("Odd cannot be null");
        }

        // checks if the odd already exists in the list by comparing the name
        if (odds.stream().anyMatch(existingOdd -> existingOdd.getName().equals(odd.getName()))) {
            log.error("Odd {} already exists in the event", odd.getName());
            throw new DomainAttributeException("Odd " + odd.getName() + " already exists in the event");
        }

        odds.add(odd);
    }


    /**
     * This method removes an odd from the event
     *
     * @param odd the odd to remove
     * @throws DomainAttributeException if the odd does not exist in the event
     * @throws DomainAttributeException if the odd is null
     */
    public void removeOdd(Odd odd) {

        //null check
        if (odd == null) {
            log.error("Odd cannot be null");
            throw new DomainAttributeException("Odd cannot be null");
        }

        // checks if the odd exists in the list by comparing the name
        if (odds.stream().noneMatch(existingOdd -> existingOdd.getName().equals(odd.getName()))) {
            log.error("Odd {} does not exist in the event", odd.getName());
            throw new DomainAttributeException("Odd " + odd.getName() + " does not exist in the event");
        }

        odds.remove(odd);

    }


    /**
     * This method ends the event by setting the odds as won or lost
     *
     * @param winningOdds the list of winning odds
     * @throws DomainAttributeException if the winning odds are null
     */
    public void endEvent(List<UUID> winningOdds) {

        // null check
        if (winningOdds == null) {
            log.error("Winning odds cannot be null");
            throw new DomainAttributeException("Winning odds cannot be null");
        }

        //check if the list of winning odds only has odds that are in the event
        if (winningOdds.stream().anyMatch(oddId -> odds.stream().noneMatch(odd -> odd.getId().equals(oddId)))) {
            log.error("Winning odds must be in the event");
            throw new DomainAttributeException("Winning odds must be in the event");
        }

        for (Odd odd : odds) {
            odd.evaluate(winningOdds.contains(odd.getId()));
        }

        isEnded = true;
    }

}
