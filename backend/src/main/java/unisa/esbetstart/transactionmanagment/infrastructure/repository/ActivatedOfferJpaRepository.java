package unisa.esbetstart.transactionmanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.ActivatedOfferEntity;

import java.util.UUID;

public interface ActivatedOfferJpaRepository extends JpaRepository<ActivatedOfferEntity, UUID> {
}
