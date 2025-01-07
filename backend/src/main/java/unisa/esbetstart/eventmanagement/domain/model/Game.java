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
public class Game implements Searchable {

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

    public void addCompetition(Competition competition) {

        if(competitions.contains(competition)) {
            log.error("Competition {} already exists in {}", competition.getName(), name);
            throw new DomainAttributeException("Competition " + competition.getName() + " already exists in " + name);
        }

        competitions.add(competition);
    }

    public void updateGame(String name, Set<Rule> rules) {

        if(name == null || rules == null) {
            log.error("Game name or rules cannot be null");
            throw new DomainAttributeException("Game name or rules cannot be null");
        }

        if(name.length() > 30) {
            log.error("Game name cannot be longer than 30 characters");
            throw new DomainAttributeException("Game name cannot be longer than 30 characters");
        }

        this.name = name;
        this.rules = rules;
    }

}
