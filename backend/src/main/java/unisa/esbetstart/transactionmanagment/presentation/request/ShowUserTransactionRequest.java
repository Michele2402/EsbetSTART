package unisa.esbetstart.transactionmanagment.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unisa.esbetstart.transactionmanagment.domain.enums.TransactionTypeEnum;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShowUserTransactionRequest {

    private String gamblerId;
    private TransactionTypeEnum type;
}
