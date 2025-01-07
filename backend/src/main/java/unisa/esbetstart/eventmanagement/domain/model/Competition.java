package unisa.esbetstart.eventmanagement.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.DomainAttributeException;

import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class Competition {

    private UUID id;
    private String name;
    private String originCountry;
    private Game game;

    @Builder
    public Competition(UUID id, String name, String originCountry, Game game) {

        this.id = id;
        this.name = name;
        this.originCountry = originCountry;
        this.game = game;

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

}
