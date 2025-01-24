package unisa.esbetstart.transactionmanagment.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.transactionmanagment.application.port.out.GetTransactionPortOut;
import unisa.esbetstart.transactionmanagment.domain.model.Transaction;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.TransactionEntity;
import unisa.esbetstart.transactionmanagment.infrastructure.mapper.InfrastructureTransactionMapper;
import unisa.esbetstart.transactionmanagment.infrastructure.repository.TransactionJpaRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionAdapterService implements GetTransactionPortOut {

    private final TransactionJpaRepository transactionJpaRepository;
    private final InfrastructureTransactionMapper infrastructureTransactionMapper;

    @Override
    public Set<Transaction> getAllTransactions() {

        List<TransactionEntity> transactions = transactionJpaRepository.findAllWithGambler();

        return infrastructureTransactionMapper.toTransactionModelWithGambler(transactions);
    }
}
