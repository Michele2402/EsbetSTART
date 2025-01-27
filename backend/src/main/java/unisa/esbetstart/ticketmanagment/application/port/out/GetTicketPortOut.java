package unisa.esbetstart.ticketmanagment.application.port.out;

import unisa.esbetstart.ticketmanagment.domain.model.Ticket;

import java.util.UUID;

public interface GetTicketPortOut {
        Ticket getTicketById(UUID ticketId);
        void updateTicket(Ticket ticket);
}
