package unisa.esbetstart.eventmanagement.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unisa.esbetstart.eventmanagement.infrastructure.entity.EventEntity;

import java.util.UUID;

public interface EventJpaRepository extends JpaRepository<EventEntity, UUID> {


}
