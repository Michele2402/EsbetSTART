package unisa.esbetstart.transactionmanagment.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class OfferEntity {

    @Id
    @Column(columnDefinition = "UUID")
    private UUID id;

    private String description;
    private double price;
    private String name;
    private LocalDateTime expiration_date;
    private String type;
    private double goal;

}
