package unisa.esbetstart.ticketmanagment.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.ticketmanagment.domain.enums.SenderEnum;
import unisa.esbetstart.ticketmanagment.domain.enums.TicketStatusEnum;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class Ticket {

    private UUID id;
    private String category;
    private TicketStatusEnum status;
    private String assignedOperator;
    private Gambler openedBy;
    private List<Message> messages = new ArrayList<>();

    @Builder
    public Ticket(String category, String assignedOperator, TicketStatusEnum status, UUID id, List<Message> messages, Gambler openedBy) {
        this.category = category;
        this.assignedOperator = assignedOperator;
        this.status = status;
        this.id = id;
        this.messages = messages;
        this.openedBy = openedBy;
    }

    /**
     * Method to add a message to the ticket
     * @param message the message to add
     */
    public void sendMessage(Message message) {

        if(message == null) {
            log.error("Sent message cannot be null");
            throw new ObjectIsNullException("Sent message cannot be null");
        }

        message.setTicket(this);
        this.messages.add(message);
    }

    /**
     * Method to read messages
     * @param sender the sender of the message
     * @return List<Message>
     */
    public List<Message> readMessages(SenderEnum sender) {
        List<Message> readMessages = new ArrayList<>();

        for(Message message : messages) {
            if(message.getSender() != sender) {
                message.setRead(true);
                readMessages.add(message);
            }
        }

        return readMessages;
    }

}