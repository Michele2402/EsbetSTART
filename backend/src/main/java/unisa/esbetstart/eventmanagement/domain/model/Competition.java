package unisa.esbetstart.eventmanagement.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.DomainAttributeException;


import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class Competition implements Searchable{

    private UUID id;
    private String name;
    private String originCountry;
    private Game game;
    private Set<Event> events;

    @Builder
    public Competition(UUID id, String name, String originCountry, Game game, Set<Event> events) {

        this.id = id;
        this.name = name;
        this.originCountry = originCountry;
        this.game = game;
        this.events = events;

    }


    /**
     * This method updates the competition name and origin country
     * @param name the new name of the competition
     * @param originCountry the new origin country of the competition
     */
    public void updateCompetition(String name, String originCountry) {

        if(name == null || originCountry == null) {
            log.error("Competition name or origin country cannot be null");
            throw new DomainAttributeException("Competition name or origin country cannot be null");
        }

        if(name.length() > 30) {
            log.error("Competition name cannot be longer than 30 characters");
            throw new DomainAttributeException("Competition name cannot be longer than 30 characters");
        }

        if(name.isEmpty() || originCountry.isEmpty()) {
            log.error("Competition name or origin country cannot be empty");
            throw new DomainAttributeException("Competition name or origin country cannot be empty");
        }

        this.name = name;
        this.originCountry = originCountry;
    }

    /**
     * This method adds an event to the competition.
     * It checks if an event with the same name exists in the list before adding.
     *
     * @param event the event to be added
     * @throws DomainAttributeException if an event with the same name already exists
     * @throws DomainAttributeException if the event is null
     */
    public void addEvent(Event event) {

        //null check
        if(event == null) {
            log.error("Event cannot be null");
            throw new DomainAttributeException("Event cannot be null");
        }

        // checks if an event with the same name exists in the list
        if (events.stream().anyMatch(existingEvent -> existingEvent.getName().equals(event.getName()))) {
            log.error("Event {} already exists in the competition", event.getName());
            throw new DomainAttributeException("Event " + event.getName() + " already exists in the competition");
        }


        events.add(event);
        event.setCompetition(this);

    }

    /**
     * This method removes an event from the competition.
     * It checks if the event exists in the list before removing.
     *
     * @param event the event to be removed
     * @throws DomainAttributeException if the event does not exist in the competition
     * @throws DomainAttributeException if the event is null
     */
    public void removeEvent(Event event) {

        //null check
        if(event == null) {
            log.error("Event cannot be null");
            throw new DomainAttributeException("Event cannot be null");
        }

        if (events.stream().noneMatch(existingEvent -> existingEvent.getName().equals(event.getName()))) {
            log.error("Event {} does not exist in the competition", event.getName());
            throw new DomainAttributeException("Event " + event.getName() + " does not exist in the competition");
        }

        events.remove(event);
    }

    /**
     * This method checks if the competition has events that are not ended.
     * @return true if the competition has events that are not ended, false otherwise
     */
    public boolean hasNotEndedEvents() {
        return events.stream().anyMatch(event -> !event.isEnded());
    }

}
