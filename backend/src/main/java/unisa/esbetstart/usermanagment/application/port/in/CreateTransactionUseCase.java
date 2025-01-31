package unisa.esbetstart.usermanagment.application.port.in;

import unisa.esbetstart.usermanagment.presentation.request.CreateTransactionRequest;

public interface CreateTransactionUseCase {

    /**
     * Saves a transaction in the database
     * @param request the request
     */
    void createTransaction(CreateTransactionRequest request);

}
