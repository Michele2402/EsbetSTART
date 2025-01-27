package unisa.esbetstart.slipmanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unisa.esbetstart.slipmanagment.infrastructure.entity.BetPlacedEntity;

import java.util.UUID;

public interface BetPlacedJpaRepository extends JpaRepository<BetPlacedEntity, UUID> {
}
