package unisa.esbetstart.eventmanagement.infrastructure.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import unisa.esbetstart.eventmanagement.infrastructure.entity.OddEntity;

import java.util.Optional;
import java.util.UUID;

public interface OddJpaRepository extends JpaRepository<OddEntity, UUID> {

    @EntityGraph(attributePaths = {"event"})
    @Query("SELECT o FROM OddEntity o WHERE o.id = :oddId")
    Optional<OddEntity> findByIdWithEvent(UUID oddId);

}
