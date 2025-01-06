package unisa.esbetstart.eventmanagement.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unisa.esbetstart.eventmanagement.infrastructure.entity.CompetitionEntity;

import java.util.UUID;

public interface CompetitionJpaRepository extends JpaRepository<CompetitionEntity, UUID> {
}
