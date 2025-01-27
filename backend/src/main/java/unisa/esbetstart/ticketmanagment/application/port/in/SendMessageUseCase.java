package unisa.esbetstart.ticketmanagment.application.port.in;

import unisa.esbetstart.ticketmanagment.presentation.request.SendMessageRequest;

public interface SendMessageUseCase {
    void sendMessage(SendMessageRequest request);
}
