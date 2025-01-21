// RemoveEventManagerService.java
package unisa.esbetstart.eventmanagement.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.eventmanagement.application.port.in.RemoveEventUseCase;
import unisa.esbetstart.eventmanagement.application.port.out.GetEventPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.RemoveEventPortOut;
import unisa.esbetstart.usermanagment.application.utils.CheckTypeAttribute;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RemoveEventManagerService implements RemoveEventUseCase {

    private final CheckTypeAttribute checkTypeAttribute;
    private final RemoveEventPortOut removeEventPortOut;
    private final GetEventPortOut getEventPortOut;

    @Override
    public void removeEvent(String eventId) {
        log.info("Removing event with id: {}", eventId);

        // Check if eventId is null or invalid
        UUID id = checkTypeAttribute.checkUUIDIsNullOrInvalid(eventId, "Event Id in remove call");

        // Check if the event exists
        if (getEventPortOut.getEventById(id) == null) {
            log.error("Event with id {} not found", eventId);
            throw new ObjectIsNullException("Event with id " + eventId + " not found");
        }

        // Remove the event
        removeEventPortOut.removeEvent(id);

        log.info("Event with id {} removed", eventId);
    }
}