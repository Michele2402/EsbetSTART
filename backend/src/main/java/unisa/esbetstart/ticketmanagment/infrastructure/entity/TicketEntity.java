package unisa.esbetstart.ticketmanagment.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "ticket")
public class TicketEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;
    private String category;
    private String assignedOperator;

    @OneToMany(mappedBy = "ticket")
    private List<MessageEntity> messages;

    @ManyToOne
    @JoinColumn(name = "gambler_id", nullable = false)
    private GamblerEntity gambler;
}
