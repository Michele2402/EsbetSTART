package unisa.esbetstart.transactionmanagment.presentation.mapper;

import org.springframework.stereotype.Component;
import unisa.esbetstart.transactionmanagment.domain.model.Transaction;
import unisa.esbetstart.transactionmanagment.presentation.response.TransactionResponse;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PresentationTransactionMapper {

    public Set<TransactionResponse> toTransactionResponseSet(Set<Transaction> transactions) {
        return transactions.stream()
                .map(this::toTransactionResponse)
                .collect(Collectors.toSet());
    }

    public TransactionResponse toTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .date(transaction.getDate())
                .build();
    }
}
