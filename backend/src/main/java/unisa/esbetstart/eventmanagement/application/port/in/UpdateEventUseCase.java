package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.presentation.request.UpdateEventRequest;

public interface UpdateEventUseCase {

    void updateEvent(UpdateEventRequest request);

}
