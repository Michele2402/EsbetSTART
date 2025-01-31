package unisa.esbetstart.eventmanagement.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import unisa.esbetstart.eventmanagement.application.port.in.GetGameUseCase;
import unisa.esbetstart.eventmanagement.application.port.out.GetGamePortOut;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.presentation.response.GameWithRulesResponse;

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

    @Override
    public Page<Game> getFiltered(String name, int size, int page) {
        Page<Game> pageResponse = getGamePortOut.getFiltered(name, PageRequest.of(page, size));
        return pageResponse;
    }
}
