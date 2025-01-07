package unisa.esbetstart.slipmanagment.infrastructure.entity;


import jakarta.persistence.*;
import lombok.*;

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
    private String result;
    private String competition;
    private String game;

    @ManyToOne
    @JoinColumn(name = "oddStatic_id", nullable = false)
    private OddStaticEntity oddStaticEntity;

    //TODO aggiungere la relazione con la classe User
}
