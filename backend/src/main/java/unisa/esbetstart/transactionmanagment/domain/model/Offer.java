package unisa.esbetstart.transactionmanagment.domain.model;

//all the tags

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.transactionmanagment.domain.enums.OfferTypeEnum;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class Offer {

    private UUID id;
    private String description;
    private String name;
    private LocalDateTime expirationDate;
    private OfferTypeEnum type;
    private double goal;
    private double price;

    @Builder
    public Offer(UUID id, String description, String name, LocalDateTime expirationDate, OfferTypeEnum type, double goal, double price) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.expirationDate = expirationDate;
        this.type = type;
        this.goal = goal;
        this.price = price;
    }

}
