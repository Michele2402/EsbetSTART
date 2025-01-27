package unisa.esbetstart.ticketmanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.ticketmanagment.application.port.in.AcceptTicketUseCase;
import unisa.esbetstart.ticketmanagment.application.port.out.GetTicketPortOut;
import unisa.esbetstart.ticketmanagment.domain.enums.SenderEnum;
import unisa.esbetstart.ticketmanagment.domain.enums.TicketStatusEnum;
import unisa.esbetstart.ticketmanagment.domain.model.Message;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;
import unisa.esbetstart.ticketmanagment.presentation.request.AcceptTicketRequest;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AcceptTicketManagerService implements AcceptTicketUseCase {

    private final GetTicketPortOut getTicketPortOut;
    private final ParseAttribute parseAttribute;

    @Override
    public void acceptTicket(AcceptTicketRequest request) {
        log.info("Accepting ticket: {}", request.getTicketId());

        // Check if the ticket ID is valid
        UUID ticketId = parseAttribute.checkUUIDIsNullOrInvalid(request.getTicketId(), "Ticket ID");

        // Get the ticket and the gambler
        Ticket ticket = getTicketPortOut.getTicketById(ticketId);

        if(ticket == null) {
            log.error("Ticket not found");
            throw new ObjectNotFoundException("Ticket not found");
        }

        // Set the ticket status to OPEN
        ticket.setStatus(TicketStatusEnum.OPEN);

        LocalDateTime date = parseAttribute.convertStringIntoDate(request.getMessageDate(), "acceptDate");

        ticket.getMessages().add(Message.builder()
                .id(UUID.randomUUID())
                .text(request.getMessageText())
                .date(date)
                .sender(SenderEnum.OPERATOR)
                .read(false)
                .ticket(ticket)
                .build());

        // Update the ticket
        getTicketPortOut.updateTicket(ticket);

        log.info("Ticket accepted");
    }
}
