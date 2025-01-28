package unisa.esbetstart.usermanagment.application.port.in;

import unisa.esbetstart.usermanagment.presentation.request.CreateTransactionRequest;

public interface CreateTransactionUseCase {

    void createTransaction(CreateTransactionRequest request);

}
