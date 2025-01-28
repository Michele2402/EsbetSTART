package unisa.esbetstart.ticketmanagment.application.port.in;

import unisa.esbetstart.ticketmanagment.presentation.request.ReadMessageRequest;

public interface ReadMessageUseCase {
    void readMessages(ReadMessageRequest request);
}
