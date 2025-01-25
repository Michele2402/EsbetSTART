// RemoveEventUseCase.java
package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.presentation.request.EndEventRequest;

import java.util.UUID;

public interface RemoveEventUseCase {
    void removeEvent(String eventId);
    void endEvent(EndEventRequest eventId);
}