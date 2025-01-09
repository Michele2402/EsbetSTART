package unisa.esbetstart.eventmanagement.infrastructure.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unisa.esbetstart.eventmanagement.infrastructure.entity.GameEntity;

import java.util.Optional;
import java.util.UUID;

public interface GameJpaRepository extends JpaRepository<GameEntity, UUID> {

/*    @EntityGraph(attributePaths = {"competitions"})
    @Query("SELECT g FROM GameEntity g WHERE g.id = :gameId")
    Optional<GameEntity> findByIdWithCompetitions(UUID gameId);*/

    Optional<GameEntity> findByName(String gameName);

}
