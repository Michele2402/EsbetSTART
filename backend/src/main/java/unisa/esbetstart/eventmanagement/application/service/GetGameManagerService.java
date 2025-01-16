package unisa.esbetstart.eventmanagement.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.eventmanagement.application.port.in.GetGameUseCase;
import unisa.esbetstart.eventmanagement.application.port.out.GetGamePortOut;
import unisa.esbetstart.eventmanagement.domain.model.Game;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetGameManagerService implements GetGameUseCase {

    private final GetGamePortOut getGamePortOut;

    /**
     * Gets all games from the database.
     */
    @Override
    public Set<Game> getAllGames() {

        log.info("Getting all games");

        Set<Game> games = getGamePortOut.getAllGames();

        log.info("All games retrieved");

        return games;
    }
}
