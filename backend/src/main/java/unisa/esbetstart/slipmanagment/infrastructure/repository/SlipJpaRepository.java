package unisa.esbetstart.slipmanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unisa.esbetstart.slipmanagment.infrastructure.entity.SlipEntity;

import java.util.Optional;
import java.util.UUID;

public interface SlipJpaRepository extends JpaRepository<SlipEntity, UUID> {

    @EntityGraph(attributePaths = {"gambler"})
    @Query("SELECT s FROM SlipEntity s WHERE s.id = :slipId")
    Optional<SlipEntity> findSlipWithGambler(UUID slipId);

}

