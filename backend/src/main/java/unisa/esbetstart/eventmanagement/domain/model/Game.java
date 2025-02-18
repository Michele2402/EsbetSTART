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
public class Game implements Searchable{

    private UUID id;
    private String name;
    private Set<Competition> competitions;
    //TODO: escludere dal tostring
    private Set<Rule> rules;

    @Builder
    public Game(UUID id, String name, Set<Competition> competitions, Set<Rule> rules) {
        this.id = id;
        this.name = name;
        this.competitions = competitions;
        this.rules = rules;
    }

    /**
     * This method adds a competition to the game.
     * It checks if a competition with the same name exists in the list before adding.
     *
     * @param competition the competition to be added
     * @throws DomainAttributeException if a competition with the same name already exists
     * @throws DomainAttributeException if the competition is null
     */
    public void addCompetition(Competition competition) {

        //null check
        if (competition == null) {
            log.error("Competition cannot be null");
            throw new DomainAttributeException("Competition cannot be null");
        }

        // checks if a competition with the same name exists in the list
        if (competitions.stream().anyMatch(existingCompetition -> existingCompetition.getName().equals(competition.getName()))) {
            log.error("Competition {} already exists in the game", competition.getName());
            throw new DomainAttributeException("Competition " + competition.getName() + " already exists in the game");
        }

        competitions.add(competition);
        competition.setGame(this);
    }

    /**
     * This method removes a competition from the game.
     *
     * @param competition the competition to be removed
     * @throws DomainAttributeException if the competition is null
     * @throws DomainAttributeException if the competition does not exist in the game
     */
    public void removeCompetition(Competition competition) {

        //null check
        if (competition == null) {
            log.error("Competition cannot be null");
            throw new DomainAttributeException("Competition cannot be null");
        }

        // checks if the competition exists in the list
        if (!competitions.contains(competition)) {
            log.error("Competition {} does not exist in the game", competition.getName());
            throw new DomainAttributeException("Competition " + competition.getName() + " does not exist in the game");
        }

        competitions.remove(competition);
    }


    /**
     * This method updates the game name and rules
     *
     * @param name  the new name of the game
     * @param rules the new rules of the game
     */
    public void updateGame(String name, Set<Rule> rules) {

        if (name == null || rules == null) {
            log.error("Game name or rules cannot be null");
            throw new DomainAttributeException("Game name or rules cannot be null");
        }

        //isempty check
        if (name.isEmpty()) {
            log.error("Game name cannot be empty");
            throw new DomainAttributeException("Game name cannot be empty");
        }

        if (name.length() > 30) {
            log.error("Game name cannot be longer than 30 characters");
            throw new DomainAttributeException("Game name cannot be longer than 30 characters");
        }

        this.name = name;
        this.rules = rules;

    }

    /**
     * This method checks if there are events that have not ended in the competitions of the game.
     *
     * @return true if there are events that have not ended, false otherwise
     */
    public boolean hasNotEndedEventsInCompetitions() {

        for (Competition competition : competitions) {
            if (competition.hasNotEndedEvents()) {
                return true;
            }
        }
        return false;
    }

}
