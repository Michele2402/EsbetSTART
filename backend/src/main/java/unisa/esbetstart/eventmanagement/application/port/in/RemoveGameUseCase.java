package unisa.esbetstart.eventmanagement.application.port.in;

public interface RemoveGameUseCase {

    /**
     * Removes a game from the database.
     * @param gameId the id of the game to remove
     */
    void removeGame(String gameId);
}
