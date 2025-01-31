package unisa.esbetstart.transactionmanagment.infrastructure.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.ActivatedOfferEntity;

import java.util.List;
import java.util.UUID;

public interface ActivatedOfferJpaRepository extends JpaRepository<ActivatedOfferEntity, UUID> {

    @EntityGraph(attributePaths = "offer")
    @Query("select a from ActivatedOfferEntity a where a.gambler.email = :gamblerEmail")
    List<ActivatedOfferEntity> findAllByGamblerEmailWithOffers(String gamblerEmail); // <2>
}
