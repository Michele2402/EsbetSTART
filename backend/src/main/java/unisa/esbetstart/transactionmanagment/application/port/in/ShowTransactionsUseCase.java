package unisa.esbetstart.transactionmanagment.application.port.in;

import unisa.esbetstart.transactionmanagment.domain.model.Transaction;
import unisa.esbetstart.transactionmanagment.presentation.request.ShowUserTransactionRequest;

import java.util.Set;

public interface ShowTransactionsUseCase {

    Set<Transaction> showTransactions(ShowUserTransactionRequest request);
    Set<Transaction> showAllTransactions();
}
