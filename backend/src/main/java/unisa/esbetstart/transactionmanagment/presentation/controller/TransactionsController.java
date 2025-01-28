package unisa.esbetstart.transactionmanagment.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.transactionmanagment.application.port.in.ShowTransactionsUseCase;
import unisa.esbetstart.transactionmanagment.domain.model.Transaction;
import unisa.esbetstart.transactionmanagment.presentation.mapper.PresentationTransactionMapper;
import unisa.esbetstart.transactionmanagment.presentation.request.ShowUserTransactionRequest;
import unisa.esbetstart.transactionmanagment.presentation.response.TransactionResponse;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/transactions")
@CrossOrigin("*")
public class TransactionsController {

    private final ShowTransactionsUseCase showTransactionsUseCase;
    private final PresentationTransactionMapper presentationTransactionMapper;

    /**
     * Retrieves all transactions for a given user. The user is identified by their gambler email.
     * @param request the request containing the gambler email
     * @return a ResponseEntity containing a Set of TransactionResponse
     */
    @PostMapping("/show")
    public ResponseEntity<Set<TransactionResponse>> showTransactions(
            @RequestBody ShowUserTransactionRequest request
        ) {
        Set<Transaction> transactions = showTransactionsUseCase.showTransactions(request);

        return ResponseEntity.ok(presentationTransactionMapper.toTransactionResponseSet(transactions));
    }

    @PostMapping("/showAll")
    public ResponseEntity<Set<TransactionResponse>> showAllTransactions() {
    	Set<Transaction> transactions = showTransactionsUseCase.showAllTransactions();

    	return ResponseEntity.ok(presentationTransactionMapper.toTransactionResponseSetWithGambler(transactions));
    }
}
