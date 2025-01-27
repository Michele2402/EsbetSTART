package unisa.esbetstart.transactionmanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.OfferEntity;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface OfferJpaRepository extends JpaRepository<OfferEntity, UUID> {

    Optional<OfferEntity> findByName(String name);

}
