package unisa.esbetstart.usermanagment.infrastructure.entity;

import jakarta.persistence.*;
import unisa.esbetstart.slipmanagment.infrastructure.entity.BetPlacedEntity;
import unisa.esbetstart.slipmanagment.infrastructure.entity.SlipEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    private String email;
    private String name;
    private String surname;
    private String username;
    private String password;

    @OneToOne(mappedBy = "user")
    private SlipEntity slip;

    @OneToMany(mappedBy = "user")
    private Set<BetPlacedEntity> bets;

}
