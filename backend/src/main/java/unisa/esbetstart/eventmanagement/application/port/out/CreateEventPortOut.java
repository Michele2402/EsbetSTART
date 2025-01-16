package unisa.esbetstart.eventmanagement.application.port.out;

import unisa.esbetstart.eventmanagement.domain.model.Event;

public interface CreateEventPortOut {

    void addEvent(Event event);

}
