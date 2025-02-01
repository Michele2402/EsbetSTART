package unisa.esbetstart.slipmanagment.infrastructure.entity;


import jakarta.persistence.*;
import lombok.*;
import unisa.esbetstart.eventmanagement.infrastructure.entity.OddEntity;
import unisa.esbetstart.slipmanagment.domain.enums.ResultEnum;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class OddStaticEntity {
    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    private LocalDateTime date;
    private String name;
    private double value;
    @Enumerated(EnumType.STRING)
    private ResultEnum result;

    private String event;
    private String competition;
    private String game;

    @ManyToOne
    @JoinColumn(name = "bet_placed_id", nullable = false)
    private BetPlacedEntity betPlaced;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odd_id", nullable = true)
    private OddEntity odd;
}
