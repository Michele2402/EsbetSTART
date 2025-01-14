package unisa.esbetstart.transactionmanagment.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import unisa.esbetstart.transactionmanagment.domain.enums.OfferTypeEnum;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "offer")
public class OfferEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    private String description;
    private double price;
    private String name;
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    private OfferTypeEnum type;

    private double goal;

    @OneToMany(mappedBy = "offer")
    private Set<ActivatedOfferEntity> activatedOffers;

}
