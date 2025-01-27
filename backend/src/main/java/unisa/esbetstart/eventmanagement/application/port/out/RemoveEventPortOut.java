// RemoveEventPortOut.java
package unisa.esbetstart.eventmanagement.application.port.out;

import unisa.esbetstart.eventmanagement.domain.model.Event;

import java.util.UUID;

public interface RemoveEventPortOut {
    void removeEvent(UUID eventId);
    void endEvent(Event event);
}