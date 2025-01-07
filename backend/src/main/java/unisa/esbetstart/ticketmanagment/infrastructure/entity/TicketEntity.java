package unisa.esbetstart.ticketmanagment.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class TicketEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;
    private String category;
    private String assignedOperator;

    @OneToMany(mappedBy = "ticket")
    private List<MessageEntity> messages;
}
