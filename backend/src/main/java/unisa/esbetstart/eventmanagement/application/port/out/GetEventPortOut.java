package unisa.esbetstart.eventmanagement.application.port.out;

import unisa.esbetstart.eventmanagement.domain.model.Event;

import java.util.List;
import java.util.UUID;

public interface GetEventPortOut {

    Event getEventByIdWithoutOdds(UUID eventId);
    List<Event> getAllByCompetitionId(UUID competitionId);

}
