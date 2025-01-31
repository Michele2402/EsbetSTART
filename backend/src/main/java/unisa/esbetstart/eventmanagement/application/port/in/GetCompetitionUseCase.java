package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.domain.model.Competition;

import java.util.List;

public interface GetCompetitionUseCase {

    /**
     * Gets all competitions belonging to a specified game from the database.
     *
     * @param gameId the id of the game to get competitions for
     * @return a list of all competitions
     */
    List<Competition> getAllByGameId(String gameId);
}
