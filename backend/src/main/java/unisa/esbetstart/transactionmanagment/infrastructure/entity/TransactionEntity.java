package unisa.esbetstart.transactionmanagment.infrastructure.entity;


import jakarta.persistence.*;
import lombok.*;
import unisa.esbetstart.transactionmanagment.domain.enums.TransactionTypeEnum;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;
    private double amount;
    private TransactionTypeEnum type;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "gambler_id", nullable = false)
    private GamblerEntity gambler;

}
