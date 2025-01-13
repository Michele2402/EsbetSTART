package unisa.esbetstart.usermanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

public interface GamblerJpaRepository extends JpaRepository<GamblerEntity, String> {
}
