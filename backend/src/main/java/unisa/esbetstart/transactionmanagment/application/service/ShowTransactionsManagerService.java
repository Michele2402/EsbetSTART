package unisa.esbetstart.transactionmanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.transactionmanagment.application.port.in.ShowTransactionsUseCase;
import unisa.esbetstart.transactionmanagment.application.port.out.GetTransactionPortOut;
import unisa.esbetstart.transactionmanagment.domain.enums.TransactionTypeEnum;
import unisa.esbetstart.transactionmanagment.domain.model.Transaction;
import unisa.esbetstart.transactionmanagment.presentation.request.ShowUserTransactionRequest;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShowTransactionsManagerService implements ShowTransactionsUseCase {

    private final CheckTypeAttribute checkTypeAttribute;
    private final GetGamblerPortOut getGamblerPortOut;
    private final ParseAttribute parseAttribute;
    private final GetTransactionPortOut getTransactionPortOut;

    @Override
    public Set<Transaction> showTransactions(ShowUserTransactionRequest request) {
        log.info("Showing transactions for user: {}", request.getGamblerEmail());

        // Check if the gambler email is valid
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getGamblerEmail(), "Gambler Email");

        // Check if the type is valid
        TransactionTypeEnum type = parseAttribute.convertStringIntoTransactionType(request.getType(), "Type");

        // Get the gambler
        Gambler gambler = getGamblerPortOut.getGamblerByEmailWithTransactions(request.getGamblerEmail(), type);

        if(gambler == null) {
            log.error("Gambler with email {} not found", request.getGamblerEmail());
            throw new ObjectIsNullException("Gambler with email " + request.getGamblerEmail() + " not found");
        }

        // Get the transactions
        Set<Transaction> transactions = gambler.getTransactions();

        log.info("Transactions for user {} retrieved", request.getGamblerEmail());

        return transactions;
    }

    @Override
    public Set<Transaction> showAllTransactions() {
        log.info("Showing all transactions");

        // Get the transactions
        Set<Transaction> transactions = getTransactionPortOut.getAllTransactions();

        // Check if the transactions are null
        if(transactions == null) {
            log.error("Transactions not found");
            throw new ObjectIsNullException("Transactions not found");
        }

        log.info("All transactions retrieved");

        return transactions;
    }
}
