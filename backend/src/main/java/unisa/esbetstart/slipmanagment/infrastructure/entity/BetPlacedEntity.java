package unisa.esbetstart.slipmanagment.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import unisa.esbetstart.usermanagment.infrastructure.entity.UserEntity;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class BetPlacedEntity {
    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    private double amount;
    private String result;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
