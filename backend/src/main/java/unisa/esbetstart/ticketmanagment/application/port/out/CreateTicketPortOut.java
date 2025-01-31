package unisa.esbetstart.ticketmanagment.application.port.out;

import unisa.esbetstart.ticketmanagment.domain.model.Ticket;

public interface CreateTicketPortOut {
    void addTicket(Ticket ticket);
    void updateTicket(Ticket ticket);
}
