package unisa.esbetstart.eventmanagement.application.port.out;

import unisa.esbetstart.eventmanagement.domain.model.Game;

import java.util.Set;
import java.util.UUID;

public interface GetGamePortOut {
    Game getGameById(UUID gameId);
    Game getGameByName(String gameName);
    Set<Game> getAllGames();
}
