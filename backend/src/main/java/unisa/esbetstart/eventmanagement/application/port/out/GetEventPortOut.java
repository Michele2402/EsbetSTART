package unisa.esbetstart.eventmanagement.application.port.out;

import unisa.esbetstart.eventmanagement.domain.model.Event;

import java.util.UUID;

public interface GetEventPortOut {

    public Event getEventById(UUID eventId);

}
