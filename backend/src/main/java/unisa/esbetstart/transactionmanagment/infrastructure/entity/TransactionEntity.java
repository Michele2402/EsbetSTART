package unisa.esbetstart.transactionmanagment.infrastructure.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import unisa.esbetstart.transactionmanagment.domain.enums.TransactionTypeEnum;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class TransactionEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;
    private double amount;
    private TransactionTypeEnum type;
    private LocalDateTime date;

}
