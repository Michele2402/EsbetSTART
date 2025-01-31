package unisa.esbetstart.transactionmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.transactionmanagment.domain.model.Transaction;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.TransactionEntity;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InfrastructureTransactionMapper {

    /**
     * Maps a TransactionEntity to a domain model Transaction.
     *
     * @param transactionEntity The TransactionEntity object to map from.
     * @return A Transaction object mapped from the TransactionEntity.
     */
    public Transaction toTransactionModel(TransactionEntity transactionEntity) {
        return Transaction.builder()
                .id(transactionEntity.getId())
                .amount(transactionEntity.getAmount())
                .type(transactionEntity.getType())
                .date(transactionEntity.getDate())
                .build();
    }

    /**
     * Maps a list of TransactionEntity objects to a set of domain model Transactions,
     * including Gambler details.
     *
     * @param transactionEntities The list of TransactionEntity objects to map from.
     * @return A set of Transactions mapped from the TransactionEntity objects, including Gambler details.
     */
    public Set<Transaction> toTransactionModelWithGambler(List<TransactionEntity> transactionEntities) {
        return transactionEntities.stream()
                .map(this::toTransactionModelWithGambler)
                .collect(Collectors.toSet());
    }

    /**
     * Maps a TransactionEntity to a domain model Transaction, including Gambler details.
     *
     * @param transactionEntity The TransactionEntity object to map from.
     * @return A Transaction object mapped from the TransactionEntity, including Gambler details.
     */
    public Transaction toTransactionModelWithGambler(TransactionEntity transactionEntity) {
        return Transaction.builder()
                .id(transactionEntity.getId())
                .amount(transactionEntity.getAmount())
                .type(transactionEntity.getType())
                .date(transactionEntity.getDate())
                .gambler(Gambler.builder().email(transactionEntity.getGambler().getEmail()).build())
                .build();
    }


    /**
     * Maps a domain model Transaction to a TransactionEntity.
     *
     * @param transaction The Transaction object to map from.
     * @return A TransactionEntity object mapped from the Transaction.
     */
    public TransactionEntity toTransactionEntity(Transaction transaction) {
        return TransactionEntity.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .date(transaction.getDate())
                .gambler(GamblerEntity.builder().email(transaction.getGambler().getEmail()).build())
                .build();
    }
}
