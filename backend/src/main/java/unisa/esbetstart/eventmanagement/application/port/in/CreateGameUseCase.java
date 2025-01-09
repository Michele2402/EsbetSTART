package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.presentation.request.AddGameRequest;

public interface CreateGameUseCase {

    void createGame(AddGameRequest request);

}
