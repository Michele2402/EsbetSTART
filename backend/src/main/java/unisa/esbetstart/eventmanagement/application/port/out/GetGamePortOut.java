package unisa.esbetstart.eventmanagement.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.presentation.response.GameWithRulesResponse;

import java.util.Set;
import java.util.UUID;

public interface GetGamePortOut {

    Game getGameById(UUID gameId);
    Game getGameByName(String gameName);
    Set<Game> getAllGames();
    Game getGameByIdWithSimpleDetails(UUID gameId);
    Game getGameByIdWithCompetitionsAndEvents(UUID gameId);

    Page<Game> getFiltered(String name, Pageable pageable);
}
