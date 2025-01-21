// RemoveEventManagerService.java
package unisa.esbetstart.eventmanagement.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.eventmanagement.application.port.in.RemoveEventUseCase;
import unisa.esbetstart.eventmanagement.application.port.out.GetEventPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.RemoveEventPortOut;
import unisa.esbetstart.eventmanagement.domain.model.Event;
import unisa.esbetstart.common.utils.*;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RemoveEventManagerService implements RemoveEventUseCase {

    private final RemoveEventPortOut removeEventPortOut;
    private final GetEventPortOut getEventPortOut;
    private final ParseAttribute parseAttribute;

    @Override
    public void removeEvent(String eventId) {
        log.info("Removing event with id: {}", eventId);

        // Check if eventId is null or invalid
        UUID id = parseAttribute.checkUUIDIsNullOrInvalid(eventId, "Event Id in remove call");

        // Check if the event exists and is ended
        Event event = getEventPortOut.getEventByIdWithoutOdds(id);

        if (event == null || !event.isEnded()) {
            throw new ObjectNotFoundException("Event with id " + eventId + " not found or not ended");
        }

        // Remove the event
        removeEventPortOut.removeEvent(id);

        log.info("Event with id {} removed", eventId);
    }
}