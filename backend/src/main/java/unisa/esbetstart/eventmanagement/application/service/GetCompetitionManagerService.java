package unisa.esbetstart.eventmanagement.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.eventmanagement.application.port.in.GetCompetitionUseCase;
import unisa.esbetstart.eventmanagement.application.port.out.GetCompetitionPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetGamePortOut;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.domain.model.Game;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetCompetitionManagerService implements GetCompetitionUseCase {

    private final ParseAttribute parseAttribute;

    private final GetCompetitionPortOut getCompetitionPortOut;
    private final GetGamePortOut getGamePortOut;

    /**
     * Gets all competitions by game id.
     *
     * @param gameId the id of the game
     */
    @Override
    public List<Competition> getAllByGameId(String gameId) {

        log.info("Get all competitions by game id: {}", gameId);

        UUID gameIdUUID = parseAttribute.checkUUIDIsNullOrInvalid(gameId, "game Id");

        Game game = getGamePortOut.getGameById(gameIdUUID);

        if(game == null) {
            log.error("Game not found");
            throw new ObjectNotFoundException("Game not found");
        }

        List<Competition> competitions = getCompetitionPortOut.getAllByGameId(gameIdUUID);

        log.info("Competitions retrieved");

        return competitions;
    }
}
