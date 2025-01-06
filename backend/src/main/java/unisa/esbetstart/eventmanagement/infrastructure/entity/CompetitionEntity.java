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
@Table(name = "competition")
public class CompetitionEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    private String name;

    private String originCountry;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity game;

    //TODO da continuare
}
