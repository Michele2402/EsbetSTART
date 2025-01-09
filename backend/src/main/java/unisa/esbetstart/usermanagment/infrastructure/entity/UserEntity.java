package unisa.esbetstart.usermanagment.infrastructure.entity;

import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;
import unisa.esbetstart.slipmanagment.infrastructure.entity.BetPlacedEntity;
import unisa.esbetstart.slipmanagment.infrastructure.entity.SlipEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.OfferEntity;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public class UserEntity {

    @Id
    private String email;
    private String name;
    private String surname;
    private String username;
    private String password;

}
