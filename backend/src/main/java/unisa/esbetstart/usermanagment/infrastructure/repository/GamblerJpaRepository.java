package unisa.esbetstart.usermanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

import java.util.Optional;

public interface GamblerJpaRepository extends JpaRepository<GamblerEntity, String> {
    @EntityGraph(attributePaths = {"activatedOffers"})
    @Query("SELECT g FROM GamblerEntity g WHERE g.email = :email")
    Optional<GamblerEntity> findByEmail(String email);
}
