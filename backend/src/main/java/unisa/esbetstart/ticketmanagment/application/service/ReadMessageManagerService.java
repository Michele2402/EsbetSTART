package unisa.esbetstart.ticketmanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.ticketmanagment.application.port.in.ReadMessageUseCase;
import unisa.esbetstart.ticketmanagment.application.port.out.CreateMessagePortOut;
import unisa.esbetstart.ticketmanagment.application.port.out.GetTicketPortOut;
import unisa.esbetstart.ticketmanagment.domain.enums.SenderEnum;
import unisa.esbetstart.ticketmanagment.domain.model.Message;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;
import unisa.esbetstart.ticketmanagment.presentation.request.ReadMessageRequest;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReadMessageManagerService implements ReadMessageUseCase {

    private final ParseAttribute parseAttribute;
    private final GetTicketPortOut getTicketPortOut;
    private final CreateMessagePortOut createMessagePortOut;

    @Override
    public void readMessages(ReadMessageRequest request) {
        log.info("Reading messages...");

        // Parse the request
        UUID ticketId = parseAttribute.checkUUIDIsNullOrInvalid(request.getTicketId(), "ticketId");
        SenderEnum sender = parseAttribute.convertStringIntoSenderType(request.getSender(), "sender");

        // Get the ticket
        Ticket ticket = getTicketPortOut.getTicketById(ticketId);

        if(ticket == null) {
            log.error("Ticket not found");
            throw new ObjectNotFoundException("Ticket not found");
        }

        List<Message> messages = ticket.readMessages(sender);

        if(messages.isEmpty()) {
            log.info("There are no unread messages");
        }

        // Update the Messages
        for (Message message : messages) {
            createMessagePortOut.updateMessage(message);
        }
    }

}
