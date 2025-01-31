package unisa.esbetstart.ticketmanagment.application.port.in;

import unisa.esbetstart.ticketmanagment.presentation.request.SendMessageRequest;

public interface SendMessageUseCase {

    /**
     * This method sends a message in a ticket
     * @param request
     */
    void sendMessage(SendMessageRequest request);

}
