package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.presentation.request.AddEventRequest;

public interface CreateEventUseCase {

    /**
     * Adds an event to the database.
     * @param request the AddEventRequest to create
     */
    void createEvent(AddEventRequest request);

}
