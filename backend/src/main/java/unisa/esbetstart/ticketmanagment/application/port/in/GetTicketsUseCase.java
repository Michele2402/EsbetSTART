package unisa.esbetstart.ticketmanagment.application.port.in;

import unisa.esbetstart.ticketmanagment.domain.model.Ticket;

import java.util.List;

public interface GetTicketsUseCase {

    /**
     * This method all tickets related to a gambler
     * @param gamblerEmail
     * @return Ticket
     */
    List<Ticket> getTicketsByGamblerEmail(String gamblerEmail);

    /**
     * This method gets all tickets related to an operator
     * @param operatorId
     * @return Ticket
     */
    List<Ticket> getTicketsByAssignedOperatorId(String operatorId);
}
