package unisa.esbetstart.transactionmanagment.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

@NoArgsConstructor
@Data
@Slf4j
public class ActivatedOffer {

    private Gambler gambler;
    private Offer offer;
    private double progress;

    @Builder
    public ActivatedOffer(Gambler gambler, Offer offer, double progress) {
        this.gambler = gambler;
        this.offer = offer;
        this.progress = progress;
    }

}
