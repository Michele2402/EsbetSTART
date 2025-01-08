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
public class Game {

    private UUID id;
    private String name;
    private Set<Competition> competitions;
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
     */
    public void addCompetition(Competition competition) {
        // checks if a competition with the same name exists in the list
        if (competitions.stream().anyMatch(existingCompetition -> existingCompetition.getName().equals(competition.getName()))) {
            log.error("Competition {} already exists in the game", competition.getName());
            throw new DomainAttributeException("Competition " + competition.getName() + " already exists in the game");
        }
        competitions.add(competition);
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

        if (name.length() > 30) {
            log.error("Game name cannot be longer than 30 characters");
            throw new DomainAttributeException("Game name cannot be longer than 30 characters");
        }

        this.name = name;
        this.rules = rules;
    }

}
