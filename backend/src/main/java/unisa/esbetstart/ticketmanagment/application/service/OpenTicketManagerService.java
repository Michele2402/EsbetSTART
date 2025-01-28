package unisa.esbetstart.ticketmanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.ticketmanagment.application.port.in.OpenTicketUseCase;
import unisa.esbetstart.ticketmanagment.application.port.out.CreateTicketPortOut;
import unisa.esbetstart.ticketmanagment.domain.enums.SenderEnum;
import unisa.esbetstart.ticketmanagment.domain.enums.TicketStatusEnum;
import unisa.esbetstart.ticketmanagment.domain.model.Message;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;
import unisa.esbetstart.ticketmanagment.presentation.request.OpenTicketRequest;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenTicketManagerService implements OpenTicketUseCase {

    private final CheckTypeAttribute checkTypeAttribute;
    private final GetGamblerPortOut getGamblerPortOut;
    private final ParseAttribute parseAttribute;
    private final CreateTicketPortOut createTicketPortOut;

    @Override
    public void openTicket(OpenTicketRequest request) {
        log.info("Opening a new ticket...");

        // Check the request
        checkOpenTicketRequest(request);

        // Parse the date attribute
        LocalDateTime messageDate = parseAttribute.convertStringIntoDate(request.getMessageDate(), "messageDate");

        // Get the gambler
        Gambler gambler = getGamblerPortOut.getGamblerByEmail(request.getGamblerEmail());

        if(gambler == null) {
            log.error("Gambler not found");
            throw new ObjectIsNullException("Gambler not found");
        }

        UUID id = UUID.randomUUID();

        // Create the list of messages
        List<Message> messages = new ArrayList<>();

        // Create the ticket
        Ticket ticket = Ticket.builder()
                .id(id)
                .category(request.getCategory())
                .status(TicketStatusEnum.PENDING)
                .assignedOperator(null)
                .openedBy(gambler)
                .build();

        // Create the first message
        messages.add(Message.builder()
                .id(UUID.randomUUID())
                .text(request.getMessageText())
                .date(messageDate)
                .sender(SenderEnum.CLIENT)
                .ticket(ticket)
                .read(false)
                .build());

        // Add the message to the ticket
        ticket.setMessages(messages);

        // Save the ticket
        createTicketPortOut.addTicket(ticket);

        log.info("Ticket opened");
    }

    private void checkOpenTicketRequest(OpenTicketRequest request) {
        if(request == null) {
            log.error("Request cannot be null");
            throw new ObjectIsNullException("Request cannot be null");
        }

        checkTypeAttribute.checkStringIsNullOrEmpty(request.getGamblerEmail(), "gamblerEmail");
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getCategory(), "category");
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getMessageText(), "messageText");
    }
}
