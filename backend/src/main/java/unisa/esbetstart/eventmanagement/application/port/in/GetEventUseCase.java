package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.domain.model.Event;

import java.util.List;

public interface GetEventUseCase {
    List<Event> getAllByCompetitionId(String competitionId);
}
