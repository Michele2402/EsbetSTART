package unisa.esbetstart.ticketmanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unisa.esbetstart.ticketmanagment.infrastructure.entity.MessageEntity;

import java.util.UUID;

public interface MessageJpaRepository extends JpaRepository<MessageEntity, UUID> {
}
