package unisa.esbetstart.eventmanagement.infrastructure.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unisa.esbetstart.eventmanagement.infrastructure.entity.EventEntity;

import java.util.Optional;
import java.util.UUID;

public interface EventJpaRepository extends JpaRepository<EventEntity, UUID> {

    /**
     * Find an event by its id with the competition entity
     * @param eventId the id of the event
     * @return the event entity
     */
    @EntityGraph(attributePaths = {"competition"})
    @Query("SELECT e FROM EventEntity e WHERE e.id = :eventId")
    Optional<EventEntity> findByIdWithCompetition(UUID eventId);

}
