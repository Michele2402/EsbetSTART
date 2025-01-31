package unisa.esbetstart.eventmanagement.application.port.in;

public interface RemoveCompetitionUseCase {

    /**
     * Removes a competition from the database.
     * @param competitionId the id of the competition to remove
     */
    void removeCompetition(String competitionId);
}
