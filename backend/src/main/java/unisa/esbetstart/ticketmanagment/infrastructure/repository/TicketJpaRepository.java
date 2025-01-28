package unisa.esbetstart.ticketmanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unisa.esbetstart.ticketmanagment.infrastructure.entity.TicketEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketJpaRepository extends JpaRepository<TicketEntity, UUID> {
    @EntityGraph(attributePaths = {"messages", "gambler"})
    Optional<TicketEntity> findById(UUID id);

    @EntityGraph(attributePaths = {"messages", "gambler"})
    @Query("SELECT t FROM TicketEntity t WHERE t.gambler.email = :gamblerEmail")
    List<Optional<TicketEntity>> findByGamblerEmail(String gamblerEmail);

    @EntityGraph(attributePaths = {"messages", "gambler"})
    @Query("SELECT t FROM TicketEntity t WHERE t.assignedOperator = :assignedOperatorEmail")
    List<Optional<TicketEntity>> findByAssignedOperator(String assignedOperatorEmail);
}
