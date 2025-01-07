package unisa.esbetstart.ticketmanagment.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class MessageEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;
    private String text;
    private String sender;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private TicketEntity ticket;

}
