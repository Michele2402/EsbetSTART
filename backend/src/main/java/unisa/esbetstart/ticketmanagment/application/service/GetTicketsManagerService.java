package unisa.esbetstart.ticketmanagment.application.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.ticketmanagment.application.port.in.GetTicketsUseCase;
import unisa.esbetstart.ticketmanagment.application.port.out.GetTicketPortOut;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.application.port.out.GetUserPortOut;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetTicketsManagerService implements GetTicketsUseCase {

    private final CheckTypeAttribute checkTypeAttribute;
    private final GetTicketPortOut getTicketPortOut;
    private final GetGamblerPortOut getGamblerPortOut;
    private final GetUserPortOut getUserPortOut;


    @Override
    public List<Ticket> getTicketsByGamblerEmail(String gamblerEmail) {
        log.info("Getting tickets by gambler email: {}", gamblerEmail);

        // Check if the gambler email is valid
        checkTypeAttribute.checkStringIsNullOrEmpty(gamblerEmail, "gamblerEmail");

        // Check if the gambler exists
        if(getGamblerPortOut.getGamblerByEmail(gamblerEmail) == null) {
            log.error("Gambler not found");
            throw new ObjectNotFoundException("Gambler not found");
        }

        List<Ticket> tickets = getTicketPortOut.getTicketsByGamblerEmail(gamblerEmail);

        // Check if the tickets are empty
        if(tickets.isEmpty()) {
            log.error("No tickets found for gambler email: {}", gamblerEmail);
            throw new ObjectNotFoundException("No tickets found for gambler email: " + gamblerEmail);
        }

        return tickets;
    }

    @Override
    public List<Ticket> getTicketsByAssignedOperatorId(String assignedOperatorEmail) {
        log.info("Getting tickets by assigned operator id: {}", assignedOperatorEmail);

        // Check if the email is valid
        checkTypeAttribute.checkStringIsNullOrEmpty(assignedOperatorEmail, "assignedOperatorId");

        if(getUserPortOut.getUserByEmail(assignedOperatorEmail) == null) {
            log.error("Operator not found");
            throw new ObjectNotFoundException("Operator not found");
        }

        List<Ticket> tickets = getTicketPortOut.getTicketsByAssignedOperatorEmail(assignedOperatorEmail);

        // Check if the tickets are empty
        if(tickets.isEmpty()) {
            log.error("No tickets found for assigned operator id: {}", assignedOperatorEmail);
            throw new ObjectNotFoundException("No tickets found for assigned operator id: " + assignedOperatorEmail);
        }

        return tickets;
    }
}
