package unisa.esbetstart.eventmanagement.infrastructure.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unisa.esbetstart.eventmanagement.infrastructure.entity.GameEntity;

import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameJpaRepository extends JpaRepository<GameEntity, UUID> {

    @EntityGraph(attributePaths = {"competitions"})
    @Query("SELECT g FROM GameEntity g WHERE g.id = :gameId")
    Optional<GameEntity> findByIdWithCompetitions(UUID gameId);

    @EntityGraph(attributePaths = {"competitions.events"})
    @Query("SELECT g FROM GameEntity g WHERE g.id = :gameId")
    Optional<GameEntity> findByIdWithCompetitionsAndEvents(UUID gameId);

    Optional<GameEntity> findByName(String name);

    @EntityGraph(attributePaths = {"rules"})
    @Query("SELECT g FROM GameEntity g")
    List<GameEntity> findAllWithRules();

    @Query(value = """
            SELECT g
            FROM GameEntity g
            WHERE (:name IS NULL OR :name = '' OR LOWER(g.name) LIKE LOWER(CONCAT('%', :name, '%')))
            """,
    countQuery = """
            SELECT COUNT(g)
            FROM GameEntity g
            WHERE (:name IS NULL OR :name = '' OR LOWER(g.name) LIKE LOWER(CONCAT('%', :name, '%')))
            """)
    Page<GameEntity> getPagination(String name, Pageable pageable);
}
