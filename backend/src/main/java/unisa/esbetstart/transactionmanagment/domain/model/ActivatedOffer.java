package unisa.esbetstart.transactionmanagment.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class ActivatedOffer {

    private UUID id;
    private Gambler gambler;
    private Offer offer;
    private double progress;

    @Builder
    public ActivatedOffer(UUID id, Gambler gambler, Offer offer, double progress) {
        this.id = id;
        this.gambler = gambler;
        this.offer = offer;
        this.progress = progress;
    }

}
