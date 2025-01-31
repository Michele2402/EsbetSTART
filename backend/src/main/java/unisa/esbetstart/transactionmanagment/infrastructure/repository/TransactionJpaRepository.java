package unisa.esbetstart.transactionmanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.TransactionEntity;

import java.util.List;
import java.util.UUID;

public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, UUID> {

    @EntityGraph(attributePaths = {"gambler"})
    @Query("SELECT t FROM TransactionEntity t")
    List<TransactionEntity> findAllWithGambler();
}