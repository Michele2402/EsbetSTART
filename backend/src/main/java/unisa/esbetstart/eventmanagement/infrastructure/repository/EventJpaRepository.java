package unisa.esbetstart.eventmanagement.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import unisa.esbetstart.eventmanagement.infrastructure.entity.EventEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface EventJpaRepository extends JpaRepository<EventEntity, UUID> {

}
