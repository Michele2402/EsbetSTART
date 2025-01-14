package unisa.esbetstart.eventmanagement.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.eventmanagement.application.port.in.AddCompetitionUseCase;
import unisa.esbetstart.eventmanagement.application.port.out.CreateCompetitionPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetGamePortOut;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.presentation.request.AddCompetitionRequest;
import unisa.esbetstart.common.utils.CheckTypeAttribute;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateCompetitionManagerService implements AddCompetitionUseCase {

    private final CheckTypeAttribute checkTypeAttribute;
    private final ParseAttribute parseAttribute;

    private final GetGamePortOut getGamePortOut;
    private final CreateCompetitionPortOut createCompetitionPortOut;

    /**
     * Adds a new competition to a game.
     * @param request the AddCompetitionRequest containing the competition data
     */
    @Override
    public void addCompetition(AddCompetitionRequest request) {

        log.info("Adding competition to game {}", request.getGameId());

        checkAddCompetitionRequest(request);

        //check and get Game id
        UUID gameId =  parseAttribute.checkUUIDIsNullOrInvalid(request.getGameId(), "Game Id");

        Competition competition = Competition.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .originCountry(request.getOriginCountry())
                .build();

        //checks if the game exists
        Game game = getGamePortOut.getGameById(gameId);

        if (game == null) {
            log.error("Game with id {} not found", gameId);
            throw new ObjectNotFoundException("Game with id " + gameId + " not found");
        }

        //add the competition
        game.addCompetition(competition);

        createCompetitionPortOut.addCompetition(competition);

        log.info("Competition {} added to game {}", competition.getName(), game.getName());
    }

    /**
     * Checks if the AddCompetitionRequest is valid.
     * @param request the AddCompetitionRequest to check
     */
    public void checkAddCompetitionRequest(AddCompetitionRequest request) {

        if(request == null) {
            log.error("AddCompetitionRequest is null");
            throw new ObjectIsNullException("AddCompetitionRequest is null");
        }

        checkTypeAttribute.checkStringIsNullOrEmpty(request.getName(), "Competition Name");
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getOriginCountry(), "Competition Origin Country");

    }
}
