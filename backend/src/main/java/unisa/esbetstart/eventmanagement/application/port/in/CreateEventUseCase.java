package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.presentation.request.AddEventRequest;

public interface CreateEventUseCase {

    void createEvent(AddEventRequest request);

}
