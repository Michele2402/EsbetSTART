package unisa.esbetstart.transactionmanagment.application.port.out;

import unisa.esbetstart.transactionmanagment.domain.model.Transaction;

public interface SaveTransactionPortOut {
    void saveTransaction(Transaction transaction);
}
