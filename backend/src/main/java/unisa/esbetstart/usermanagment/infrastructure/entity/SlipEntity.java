package unisa.esbetstart.usermanagment.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
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
