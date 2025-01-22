package unisa.esbetstart.eventmanagement.infrastructure.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unisa.esbetstart.eventmanagement.infrastructure.entity.CompetitionEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompetitionJpaRepository extends JpaRepository<CompetitionEntity, UUID> {

    @EntityGraph(attributePaths = {"game.rules", "events"})
    @Query("SELECT c FROM CompetitionEntity c WHERE c.id = :competitionId")
    Optional<CompetitionEntity> findByIdWithGameRules(UUID competitionId);

    @EntityGraph(attributePaths = {"game"})
    @Query("SELECT c FROM CompetitionEntity c WHERE c.id = :competitionId")
    Optional<CompetitionEntity> findByIdWithGame(UUID competitionId);

    @EntityGraph(attributePaths = {"events"})
    @Query("SELECT c FROM CompetitionEntity c WHERE c.id = :competitionId")
    Optional<CompetitionEntity> findByIdWithEventsList(UUID competitionId);

    List<CompetitionEntity> findAllByGameId(UUID gameId);
}
