package unisa.esbetstart.transactionmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.ActivatedOfferEntity;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.OfferEntity;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<Offer> toOfferModelSet (List<OfferEntity> offerEntitySet) {
        return offerEntitySet
                .stream()
                .map(this::toOfferModel)
                .collect(Collectors.toSet());
    }

    /**
     * Maps an ActivatedOffer to an ActivatedOfferEntity with only id and Offer
     * @param activatedOffer is the activated offer to be mapped
     * @return ActivatedOfferEntity
     */
    public ActivatedOfferEntity toActivatedOfferEntity (ActivatedOffer activatedOffer) {
        return ActivatedOfferEntity.builder()
                .progress(0.0)
                .id(activatedOffer.getId())
                .gambler(GamblerEntity.builder().
                        email(activatedOffer.getGambler().getEmail()).build()
                )
                .offer(OfferEntity.builder()
                        .id(activatedOffer.getOffer().getId()).build())
                .build();
    }

    /**
     * Maps an ActivatedOfferEntity to an ActivatedOffer with only id and Offer
     * @param activatedOfferEntity is the activated offer entity to be mapped
     * @return ActivatedOffer
     */
    public ActivatedOffer toActivatedOfferModel (ActivatedOfferEntity activatedOfferEntity) {
        return ActivatedOffer.builder()
                .id(activatedOfferEntity.getId())
                .offer(Offer.builder()
                        .id(activatedOfferEntity.getOffer().getId())
                        .build())
                .build();
    }
}
