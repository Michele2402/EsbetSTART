package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.domain.model.Game;

import java.util.Set;

public interface GetGameUseCase {
    Set<Game> getAllGames();
}
