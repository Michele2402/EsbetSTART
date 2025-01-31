package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.presentation.request.UpdateGameRequest;

public interface UpdateGameUseCase {

    /**
     * Updates a game in the database.
     * @param request the UpdateGameRequest to update
     */
    void updateGame(UpdateGameRequest request);

}
