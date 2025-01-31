package unisa.esbetstart.transactionmanagment.application.port.in;

import unisa.esbetstart.transactionmanagment.domain.model.Transaction;
import unisa.esbetstart.transactionmanagment.presentation.request.ShowUserTransactionRequest;

import java.util.Set;

public interface ShowTransactionsUseCase {

    /**
     * Shows the transactions of a user
     * @param request the request
     * @return the set of transactions
     */
    Set<Transaction> showTransactions(ShowUserTransactionRequest request);

    /**
     * Shows all the transactions
     * @return the set of transactions
     */
    Set<Transaction> showAllTransactions();
}
