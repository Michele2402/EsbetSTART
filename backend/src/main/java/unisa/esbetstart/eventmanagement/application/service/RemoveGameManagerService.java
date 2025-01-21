package unisa.esbetstart.eventmanagement.application.service;

// src/main/java

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.eventmanagement.application.port.in.RemoveGameUseCase;
import unisa.esbetstart.eventmanagement.application.port.out.GetGamePortOut;
import unisa.esbetstart.eventmanagement.application.port.out.RemoveGamePortOut;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.common.utils.*;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RemoveGameManagerService implements RemoveGameUseCase {

    private final RemoveGamePortOut removeGamePortOut;
    private final GetGamePortOut getGamePortOut;
    private final ParseAttribute parseAttribute;

    @Override
    public void removeGame(String gameId) {
        log.info("Removing game with id: {}", gameId);

        // Check if gameId is null or invalid
        UUID id = parseAttribute.checkUUIDIsNullOrInvalid(gameId, "Game Id in remove call");

        // Check if the game exists and has no running events in its competitions
        Game game = getGamePortOut.getGameByIdWithCompetitionsAndEvents(id);

        if (game == null || game.hasNotEndedEventsInCompetitions()) {
            throw new ObjectNotFoundException("Game with id " + gameId + " not found or has running events in competitions");
        }

        // Remove the game
        removeGamePortOut.removeGame(id);

        log.info("Game with id {} removed", gameId);
    }
}
