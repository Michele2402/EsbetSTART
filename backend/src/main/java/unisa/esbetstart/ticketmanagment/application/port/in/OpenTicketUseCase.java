package unisa.esbetstart.ticketmanagment.application.port.in;

import unisa.esbetstart.ticketmanagment.presentation.request.OpenTicketRequest;

public interface OpenTicketUseCase {

    /**
     * This method opens a ticket
     * @param request
     */
    void openTicket(OpenTicketRequest request);
}
