package unisa.esbetstart.transactionmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.ActivatedOfferEntity;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.OfferEntity;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

@Component
@RequiredArgsConstructor
public class InfrastructureOfferMapper {

    public Offer toOfferModel (OfferEntity offerEntity) {
        return Offer.builder()
                .id(offerEntity.getId())
                .description(offerEntity.getDescription())
                .expirationDate(offerEntity.getExpirationDate())
                .goal(offerEntity.getGoal())
                .name(offerEntity.getName())
                .price(offerEntity.getPrice())
                .type(offerEntity.getType())
                .build();
    }

    public OfferEntity toOfferEntity (Offer offer) {
        return OfferEntity.builder()
                .id(offer.getId())
                .description(offer.getDescription())
                .expirationDate(offer.getExpirationDate())
                .goal(offer.getGoal())
                .price(offer.getPrice())
                .type(offer.getType())
                .name(offer.getName())
                .build();
    }

    public ActivatedOfferEntity toActivatedOfferEntity (ActivatedOffer offer) {
        return ActivatedOfferEntity.builder()
                .progress(0.0)
                .id(offer.getId())
                .gambler(GamblerEntity.builder().
                        email(offer.getGambler().getEmail()).build()
                )
                .offer(OfferEntity.builder()
                        .id(offer.getOffer().getId()).build())
                .build();
    }
}
