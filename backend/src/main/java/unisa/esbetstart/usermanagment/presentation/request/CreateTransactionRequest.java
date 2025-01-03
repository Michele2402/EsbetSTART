package unisa.esbetstart.usermanagment.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unisa.esbetstart.transactionmanagment.domain.enums.TransactionTypeEnum;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionRequest {

    private String gamblerId;
    private TransactionTypeEnum transactionType;
    private Integer transactionValue;
}
