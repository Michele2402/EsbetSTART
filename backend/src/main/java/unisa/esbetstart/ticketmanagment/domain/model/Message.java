package unisa.esbetstart.ticketmanagment.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Data
public class Message {

    private Ticket ticket;
    private String text;
    private LocalDateTime date;
    private String sender; // "client" or "operator"
    private boolean read;

    @Builder
    public Message(String text, LocalDateTime date, String sender, boolean read, Ticket ticket) {

        this.text = text;
        this.date = date;
        this.sender = sender;
        this.read = read;
        this.ticket = ticket;
    }

}