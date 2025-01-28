package unisa.esbetstart.transactionmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.transactionmanagment.domain.model.Transaction;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.TransactionEntity;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InfrastructureTransactionMapper {

    public Transaction toTransactionModel(TransactionEntity transactionEntity) {
        return Transaction.builder()
                .id(transactionEntity.getId())
                .amount(transactionEntity.getAmount())
                .type(transactionEntity.getType())
                .date(transactionEntity.getDate())
                .build();
    }

    public Set<Transaction> toTransactionModelWithGambler(List<TransactionEntity> transactionEntities) {
        return transactionEntities.stream()
                .map(this::toTransactionModelWithGambler)
                .collect(Collectors.toSet());
    }

    public Transaction toTransactionModelWithGambler(TransactionEntity transactionEntity) {
        return Transaction.builder()
                .id(transactionEntity.getId())
                .amount(transactionEntity.getAmount())
                .type(transactionEntity.getType())
                .date(transactionEntity.getDate())
                .gambler(Gambler.builder().email(transactionEntity.getGambler().getEmail()).build())
                .build();
    }
}
