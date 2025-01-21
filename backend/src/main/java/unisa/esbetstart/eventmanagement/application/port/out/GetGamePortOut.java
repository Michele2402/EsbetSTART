package unisa.esbetstart.eventmanagement.application.port.out;

import unisa.esbetstart.eventmanagement.domain.model.Game;

import java.util.UUID;

public interface GetGamePortOut {

    Game getGameById(UUID gameId);
    Game getGameByName(String gameName);
    Game getGameByIdWithSimpleDetails(UUID gameId);
    Game getGameByIdWithCompetitionsAndEvents(UUID gameId);
}
