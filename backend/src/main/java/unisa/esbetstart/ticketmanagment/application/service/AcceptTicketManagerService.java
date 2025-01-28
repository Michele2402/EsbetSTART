package unisa.esbetstart.ticketmanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.ticketmanagment.application.port.in.AcceptTicketUseCase;
import unisa.esbetstart.ticketmanagment.application.port.out.GetTicketPortOut;
import unisa.esbetstart.ticketmanagment.application.port.out.CreateMessagePortOut;
import unisa.esbetstart.ticketmanagment.domain.enums.SenderEnum;
import unisa.esbetstart.ticketmanagment.domain.enums.TicketStatusEnum;
import unisa.esbetstart.ticketmanagment.domain.model.Message;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;
import unisa.esbetstart.ticketmanagment.presentation.request.AcceptTicketRequest;
import unisa.esbetstart.usermanagment.application.port.out.GetUserPortOut;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AcceptTicketManagerService implements AcceptTicketUseCase {

    private final GetTicketPortOut getTicketPortOut;
    private final ParseAttribute parseAttribute;
    private final GetUserPortOut getUserPortOut;
    private final CheckTypeAttribute checkTypeAttribute;
    private final CreateMessagePortOut createMessagePortOut;

    @Override
    public void acceptTicket(AcceptTicketRequest request) {
        log.info("Accepting ticket: {}", request.getTicketId());

        // Check if the ticket ID is valid
        UUID ticketId = parseAttribute.checkUUIDIsNullOrInvalid(request.getTicketId(), "Ticket ID");

        // Check if the assigned operator is valid
         checkTypeAttribute.checkStringIsNullOrEmpty(request.getAssignedOperator(), "assignedOperatorId");

        // Get the ticket and the gambler
        Ticket ticket = getTicketPortOut.getTicketById(ticketId);

        if(ticket == null) {
            log.error("Ticket not found");
            throw new ObjectNotFoundException("Ticket not found");
        }

        // Check if the assigned operator exists
        if(getUserPortOut.getUserByEmail(request.getAssignedOperator()) == null) {
            log.error("Assigned operator not found");
            throw new ObjectNotFoundException("Assigned operator not found");
        }

        // Set the ticket status to OPEN
        ticket.setStatus(TicketStatusEnum.OPEN);

        // Set the assigned operator
        ticket.setAssignedOperator(request.getAssignedOperator());

        LocalDateTime date = parseAttribute.convertStringIntoDate(request.getMessageDate(), "acceptDate");

        Message message = Message.builder()
                .id(UUID.randomUUID())
                .text(request.getMessageText())
                .date(date)
                .sender(SenderEnum.OPERATOR)
                .read(false)
                .ticket(ticket)
                .build();

        ticket.sendMessage(message);

        // Save the message
        createMessagePortOut.saveMessage(message);

        log.info("Ticket accepted");
    }
}
