package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.presentation.request.AddGameRequest;

public interface CreateGameUseCase {

    /**
     * Adds a game to the database.
     * @param request the AddGameRequest to create
     */
    void createGame(AddGameRequest request);

}
