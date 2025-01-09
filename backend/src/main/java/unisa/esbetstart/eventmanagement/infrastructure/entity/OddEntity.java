package unisa.esbetstart.eventmanagement.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import unisa.esbetstart.slipmanagment.infrastructure.entity.OddStaticEntity;
import unisa.esbetstart.slipmanagment.infrastructure.entity.SlipEntity;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "odd")
public class OddEntity {
    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    private double value;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;

    @ManyToOne
    @JoinColumn(name = "slip_id", nullable = false)
    private SlipEntity slip;

    @OneToMany(mappedBy = "odd")
    private Set<OddStaticEntity> oddStatics;
}
