package unisa.esbetstart.transactionmanagment.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShowUserTransactionRequest {

    private String gamblerEmail;
    private String type;
}
