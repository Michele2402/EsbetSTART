package unisa.esbetstart.transactionmanagment.domain.model;

//put here the code of the class

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.DomainAttributeException;
import unisa.esbetstart.transactionmanagment.domain.enums.TransactionTypeEnum;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class Transaction {

    private UUID id;
    private double amount;
    private TransactionTypeEnum type;
    private LocalDateTime date;
    private Gambler gambler;

    @Builder
    public Transaction(UUID id, double amount, TransactionTypeEnum type, LocalDateTime date, Gambler gambler) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.gambler = gambler;
        validate();
    }

    /**
     * Validates the transaction
     */
    public void validate() {
        if (this.amount <= 0) {
            log.error("Amount must be positive");
            throw new DomainAttributeException("Amount must be positive");
        }
    }

}
