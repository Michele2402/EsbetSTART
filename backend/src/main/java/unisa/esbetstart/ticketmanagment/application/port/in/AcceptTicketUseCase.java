package unisa.esbetstart.ticketmanagment.application.port.in;

import unisa.esbetstart.ticketmanagment.presentation.request.AcceptTicketRequest;

public interface AcceptTicketUseCase {

    /**
     * This method accepts a ticket
     * @param request
     */
    void acceptTicket(AcceptTicketRequest request);
}
