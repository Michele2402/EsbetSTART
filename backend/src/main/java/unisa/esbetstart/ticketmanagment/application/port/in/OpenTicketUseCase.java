package unisa.esbetstart.ticketmanagment.application.port.in;

import unisa.esbetstart.ticketmanagment.presentation.request.OpenTicketRequest;

public interface OpenTicketUseCase {
    void openTicket(OpenTicketRequest request);
}
