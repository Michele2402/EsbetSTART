package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.presentation.response.GameWithRulesResponse;
import org.springframework.data.domain.Page;


import java.util.Set;

public interface GetGameUseCase {

    /**
     * Gets all games from the database.
     *
     * @return a set of all games
     */
    Set<Game> getAllGames();
    Page<Game> getFiltered(String name, int size, int page);
}
