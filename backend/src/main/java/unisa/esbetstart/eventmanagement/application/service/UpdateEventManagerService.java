package unisa.esbetstart.eventmanagement.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.eventmanagement.application.port.in.UpdateEventUseCase;
import unisa.esbetstart.eventmanagement.application.port.out.UpdateEventPortOut;
import unisa.esbetstart.eventmanagement.domain.model.Event;
import unisa.esbetstart.eventmanagement.presentation.request.UpdateEventRequest;
import unisa.esbetstart.usermanagment.application.utils.CheckTypeAttribute;

import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateEventManagerService implements UpdateEventUseCase {


    private final CheckTypeAttribute checkTypeAttribute;
    private final UpdateEventPortOut updateEventPortOut;

    @Override
    public void updateEvent(UpdateEventRequest request) {

        log.info("Updating event with id: {}", request.getEventId());

        //check and get event id
        UUID eventId = checkTypeAttribute.checkUUIDIsNullOrInvalid(request.getEventId(), "Event Id in update call");

        //check the update event request
        checkUpdateEventRequest(request);

        //build the updated object
        Event updatedEvent = Event.builder()
                .id(eventId)
                .name(request.getName())
                .date(request.getDate())
                .build();

        //update the event
        updateEventPortOut.updateEvent(updatedEvent);

        log.info("Event with id {} updated", eventId.toString());

    }

    private void checkUpdateEventRequest(UpdateEventRequest request) {

        if (request == null) {
            log.error("Update event request is null");
            throw new ObjectIsNullException("Update event request is null");
        }

        checkTypeAttribute.checkStringIsNullOrEmpty(request.getName(), "Name in update event request");
        checkTypeAttribute.checkIfDateIsNullOrInThePast(request.getDate(), "Date in update event request");

    }


}
