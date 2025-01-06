package unisa.esbetstart.eventmanagement.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "rule")
public class RuleEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    private String name;

    private Integer position;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;
}
