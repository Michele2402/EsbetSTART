package unisa.esbetstart.usermanagment.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private String email;

    private String name;

    private String surname;

    private String username;

    private String password;

    @OneToOne(mappedBy = "user")
    private SlipEntity slip;
}
