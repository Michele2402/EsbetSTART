package unisa.esbetstart.ticketmanagment.application.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.ticketmanagment.application.port.in.SendMessageUseCase;
import unisa.esbetstart.ticketmanagment.application.port.out.CreateMessagePortOut;
import unisa.esbetstart.ticketmanagment.application.port.out.GetTicketPortOut;
import unisa.esbetstart.ticketmanagment.domain.enums.SenderEnum;
import unisa.esbetstart.ticketmanagment.domain.model.Message;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;
import unisa.esbetstart.ticketmanagment.presentation.request.SendMessageRequest;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendMessageManagerService implements SendMessageUseCase {

    private final ParseAttribute parseAttribute;
    private final CheckTypeAttribute checkTypeAttribute;
    private final GetTicketPortOut getTicketPortOut;
    private final CreateMessagePortOut createMessagePortOut;

    @Override
    public void sendMessage(SendMessageRequest request) {
        log.info("Sending message. . .");

        // Check the request
        checkSendMessageRequest(request);

        // Parse the ticketId
        UUID ticketId = parseAttribute.checkUUIDIsNullOrInvalid(request.getTicketId(), "ticketId");

        // Parse the Date attribute
        LocalDateTime date = parseAttribute.convertStringIntoDate(request.getDate(), "date");

        // Parse the Sender attribute
        SenderEnum sender = parseAttribute.convertStringIntoSenderType(request.getSender(), "sender");

        // Get the ticket
        Ticket ticket = getTicketPortOut.getTicketById(ticketId);

        if(ticket == null) {
            log.error("Ticket not found");
            throw new ObjectIsNullException("Ticket not found");
        }

        // Create the message
        Message message = Message.builder()
                .id(UUID.randomUUID())
                .text(request.getText())
                .date(date)
                .sender(sender)
                .read(false)
                .build();

        ticket.sendMessage(message);

        // Save the message
        createMessagePortOut.saveMessage(message);

        log.info("Message sent");
    }

    private void checkSendMessageRequest(SendMessageRequest request) {
        if(request == null) {
            log.error("Request cannot be null");
            throw new ObjectIsNullException("Request cannot be null");
        }

        checkTypeAttribute.checkStringIsNullOrEmpty(request.getText(), "text");
    }


}
