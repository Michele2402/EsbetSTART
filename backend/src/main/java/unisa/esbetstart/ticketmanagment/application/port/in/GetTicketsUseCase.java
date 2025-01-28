package unisa.esbetstart.ticketmanagment.application.port.in;

import unisa.esbetstart.ticketmanagment.domain.model.Ticket;

import java.util.List;

public interface GetTicketsUseCase {
    List<Ticket> getTicketsByGamblerEmail(String gamblerEmail);
    List<Ticket> getTicketsByAssignedOperatorId(String operatorId);
}
