package unisa.esbetstart.ticketmanagment.application.port.in;

import unisa.esbetstart.ticketmanagment.presentation.request.ReadMessageRequest;

public interface ReadMessageUseCase {

    /**
     * This method changes the status of a message to read
     * @param request
     */
    void readMessages(ReadMessageRequest request);
}
