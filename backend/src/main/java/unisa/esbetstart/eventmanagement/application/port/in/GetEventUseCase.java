package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.domain.model.Event;

import java.util.List;

public interface GetEventUseCase {

    /**
     * Gets all events belonging to a specified competition from the database.
     *
     * @param competitionId the id of the competition to get events for
     * @return a list of all events
     */
    List<Event> getAllByCompetitionId(String competitionId);
}
