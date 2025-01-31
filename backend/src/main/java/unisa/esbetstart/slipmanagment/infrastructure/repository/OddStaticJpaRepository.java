package unisa.esbetstart.slipmanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unisa.esbetstart.slipmanagment.infrastructure.entity.BetPlacedEntity;
import unisa.esbetstart.slipmanagment.infrastructure.entity.OddStaticEntity;

import java.util.Optional;
import java.util.UUID;

public interface OddStaticJpaRepository extends JpaRepository<OddStaticEntity, UUID> {

    @EntityGraph(attributePaths = {"betPlaced.gambler"})
    @Query("SELECT o FROM OddStaticEntity o WHERE o.id = :oddStaticId")
    Optional<OddStaticEntity> findOddStaticEntityToGambler(UUID oddStaticId);

}
