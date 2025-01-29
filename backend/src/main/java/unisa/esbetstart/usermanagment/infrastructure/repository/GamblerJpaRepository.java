package unisa.esbetstart.usermanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unisa.esbetstart.transactionmanagment.domain.enums.TransactionTypeEnum;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

import java.util.Optional;

public interface GamblerJpaRepository extends JpaRepository<GamblerEntity, String> {
    @EntityGraph(attributePaths = {"activatedOffers"})
    @Query("SELECT g FROM GamblerEntity g WHERE g.email = :email")
    Optional<GamblerEntity> findByEmailWithActivatedOffers(String email);

    @Query("SELECT g FROM GamblerEntity g JOIN FETCH g.transactions t WHERE g.email = :email AND t.type = :typeEnum")
    Optional<GamblerEntity> findByEmailWithTransactions(String email, TransactionTypeEnum typeEnum);

    @EntityGraph(attributePaths = {"bets.odds.odd", "bets.odds"})
    @Query("SELECT g FROM GamblerEntity g WHERE g.email = :email")
    Optional<GamblerEntity> findByEmailWithBets(String email);

    @EntityGraph(attributePaths = {"bets.odds.odd", "bets.odds"})
    @Query("SELECT g FROM GamblerEntity g " +
            "JOIN FETCH g.bets b " +
            "JOIN FETCH b.odds o " +
            "JOIN FETCH o.odd " +
            "WHERE g.email = :email AND b.result = 'PLAYING'")
    Optional<GamblerEntity> findByEmailWithRunningBets(String email);

    Optional<GamblerEntity> findByEmail(String email);

    /**
     * Find a gambler by email up until offers
     * @param email
     * @return Optional of GamblerEntity
     */
    @EntityGraph(attributePaths = {"activatedOffers.offer"})
    @Query("SELECT g FROM GamblerEntity g WHERE g.email = :email")
    Optional<GamblerEntity> findByEmailWithOffers(String email);

}
