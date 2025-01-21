package unisa.esbetstart.eventmanagement.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "game")
public class GameEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private Set<CompetitionEntity> competitions;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RuleEntity> rules;
}
