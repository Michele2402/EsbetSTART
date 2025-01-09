package unisa.esbetstart.transactionmanagment.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "activated_offer")
public class ActivatedOfferEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    private Double progress;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private OfferEntity offer;

    @ManyToOne
    @JoinColumn(name = "gambler_id", nullable = false)
    private GamblerEntity gambler;

}
