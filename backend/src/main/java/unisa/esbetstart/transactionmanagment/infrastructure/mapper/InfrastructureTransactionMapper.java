package unisa.esbetstart.transactionmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.transactionmanagment.domain.model.Transaction;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.TransactionEntity;

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
}
