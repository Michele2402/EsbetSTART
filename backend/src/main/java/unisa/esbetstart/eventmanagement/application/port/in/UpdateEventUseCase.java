package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.presentation.request.UpdateEventRequest;

public interface UpdateEventUseCase {

    /**
     * Updates an event in the database.
     * @param request the UpdateEventRequest to update
     */
    void updateEvent(UpdateEventRequest request);

}
