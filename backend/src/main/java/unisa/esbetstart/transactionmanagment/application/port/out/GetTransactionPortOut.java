package unisa.esbetstart.transactionmanagment.application.port.out;

import unisa.esbetstart.transactionmanagment.domain.model.Transaction;

import java.util.Set;

public interface GetTransactionPortOut {
    Set<Transaction> getAllTransactions();
}
