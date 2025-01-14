package unisa.esbetstart.transactionmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.OfferEntity;

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
}
