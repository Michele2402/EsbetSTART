package unisa.esbetstart.ticketmanagment.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import unisa.esbetstart.ticketmanagment.domain.enums.SenderEnum;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "message")
public class MessageEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;
    private String text;
    @Enumerated(EnumType.STRING)
    private SenderEnum sender;
    private boolean read;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private TicketEntity ticket;

}
