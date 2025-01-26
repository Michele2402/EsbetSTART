package unisa.esbetstart.ticketmanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unisa.esbetstart.ticketmanagment.infrastructure.entity.TicketEntity;

import java.util.UUID;

public interface TicketJpaRepository extends JpaRepository<TicketEntity, UUID> {
}
