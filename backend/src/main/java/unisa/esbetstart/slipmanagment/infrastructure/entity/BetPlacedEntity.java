package unisa.esbetstart.slipmanagment.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import unisa.esbetstart.eventmanagement.infrastructure.entity.OddEntity;
import unisa.esbetstart.slipmanagment.domain.enums.ResultEnum;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;
import unisa.esbetstart.usermanagment.infrastructure.entity.UserEntity;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "bet_placed")
public class BetPlacedEntity {
    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    private double amount;

    //@Enumerated(EnumType.STRING)
    private ResultEnum result;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private GamblerEntity gambler;

    @OneToMany (mappedBy = "betPlaced", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OddStaticEntity> odds;

}
