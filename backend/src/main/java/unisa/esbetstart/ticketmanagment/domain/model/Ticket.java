package unisa.esbetstart.ticketmanagment.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.ticketmanagment.domain.enums.TicketStatusEnum;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.domain.model.User;

import java.util.*;

@NoArgsConstructor
@Data
@Slf4j
public class Ticket {

    private String category;
    private UUID assignedOperator;
    private TicketStatusEnum status;
    private UUID id;
    private List<Message> messages = new ArrayList<>();
    private Gambler openedBy;

    @Builder
    public Ticket(String category, UUID assignedOperator, TicketStatusEnum status, UUID id, List<Message> messages, Gambler openedBy) {

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

}