// RemoveEventUseCase.java
package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.presentation.request.EndEventRequest;

import java.util.UUID;

public interface RemoveEventUseCase {

    /**
     * Removes an event from the database.
     * @param eventId the id of the event to remove
     */
    void removeEvent(String eventId);

    /**
     * Ends an event.
     * @param eventId the id of the event to end
     */
    void endEvent(EndEventRequest eventId);
}