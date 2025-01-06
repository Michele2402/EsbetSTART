package unisa.esbetstart.usermanagment.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "slip")
public class SlipEntity {

    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "user_email", nullable = false)
    private UserEntity user;

}
