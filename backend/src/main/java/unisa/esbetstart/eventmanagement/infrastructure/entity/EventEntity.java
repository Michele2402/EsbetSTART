package unisa.esbetstart.eventmanagement.infrastructure.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class EventEntity {
    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    private String name;
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id", nullable = false)
    private CompetitionEntity competition;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<OddEntity> odds;

}
