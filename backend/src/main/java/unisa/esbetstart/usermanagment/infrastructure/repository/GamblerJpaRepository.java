package unisa.esbetstart.usermanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

import java.util.Optional;

public interface GamblerJpaRepository extends JpaRepository<GamblerEntity, String> {
    Optional<GamblerEntity> findByEmail(String email);
}
