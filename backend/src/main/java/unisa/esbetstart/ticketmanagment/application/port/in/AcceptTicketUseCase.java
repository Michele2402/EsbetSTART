package unisa.esbetstart.ticketmanagment.application.port.in;

import unisa.esbetstart.ticketmanagment.presentation.request.AcceptTicketRequest;

public interface AcceptTicketUseCase {
    void acceptTicket(AcceptTicketRequest request);
}
