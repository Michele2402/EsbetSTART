package unisa.esbetstart.slipmanagment.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import unisa.esbetstart.eventmanagement.infrastructure.entity.OddEntity;
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
@Table(name = "slip")
public class SlipEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;
    private int amount;

    @OneToOne
    @JoinColumn(name = "user_email", nullable = false)
    private GamblerEntity gambler;

    @OneToMany()
    private Set<OddEntity> odds;

}