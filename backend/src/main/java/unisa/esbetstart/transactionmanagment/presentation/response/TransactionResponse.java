package unisa.esbetstart.transactionmanagment.presentation.response;

import lombok.*;
import unisa.esbetstart.transactionmanagment.domain.enums.TransactionTypeEnum;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {

    private UUID id;
    private double amount;
    private TransactionTypeEnum type;
    private LocalDateTime date;
    private String gambler;
}
