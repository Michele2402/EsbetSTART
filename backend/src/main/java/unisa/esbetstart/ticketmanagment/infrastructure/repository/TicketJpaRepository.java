package unisa.esbetstart.ticketmanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import unisa.esbetstart.ticketmanagment.infrastructure.entity.TicketEntity;

import java.util.Optional;
import java.util.UUID;

public interface TicketJpaRepository extends JpaRepository<TicketEntity, UUID> {
    @EntityGraph(attributePaths = {"messages", "gambler"})
    Optional<TicketEntity> findById(UUID id);
}
