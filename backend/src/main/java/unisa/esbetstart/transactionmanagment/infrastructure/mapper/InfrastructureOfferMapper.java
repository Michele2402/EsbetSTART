package unisa.esbetstart.transactionmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.ActivatedOfferEntity;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.OfferEntity;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InfrastructureOfferMapper {

    /**
     * Maps an OfferEntity to an Offer
     * @param offerEntity is the offer entity to be mapped
     * @return Offer
     */
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

    /**
     * Maps an Offer to an OfferEntity
     * @param offer is the offer to be mapped
     * @return OfferEntity
     */
    public OfferEntity toOfferEntity (Offer offer) {
        return OfferEntity.builder()
                .id(offer.getId())
                .description(offer.getDescription())
                .expirationDate(offer.getExpirationDate())
                .goal(offer.getGoal())
                .price(offer.getBonus())
                .type(offer.getType())
                .name(offer.getName())
                .build();
    }

    /**
     * Maps a set of OfferEntity to a set of Offer
     * @param offerEntitySet is the set of offer entities to be mapped
     * @return Set<Offer>
     */
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
     * Maps an ActivatedOffer to an ActivatedOfferEntity with details
     * @param activatedOffer is the activated offer to be mapped
     * @return ActivatedOfferEntity
     */
    public ActivatedOfferEntity toActivatedOfferEntityWithDetails (ActivatedOffer activatedOffer) {
        return ActivatedOfferEntity.builder()
                .progress(activatedOffer.getProgress())
                .id(activatedOffer.getId())
                .gambler(GamblerEntity.builder().
                        email(activatedOffer.getGambler().getEmail()).build()
                )
                .offer(OfferEntity.builder()
                        .id(activatedOffer.getOffer().getId())
                .build())
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

    /**
     * Maps an ActivatedOfferEntity to an ActivatedOffer with details
     * @param activatedOfferEntity is the activated offer entity to be mapped
     * @return ActivatedOffer
     */
    public ActivatedOffer toActivatedOfferModelWithDetails (ActivatedOfferEntity activatedOfferEntity) {
        return ActivatedOffer.builder()
                .id(activatedOfferEntity.getId())
                .progress(activatedOfferEntity.getProgress())
                .gambler(Gambler.builder()
                        .email(activatedOfferEntity.getGambler().getEmail())
                        .build())
                .offer(toOfferModel(activatedOfferEntity.getOffer()))
                .build();
    }

    /**
     * Maps an ActivatedOfferEntity to an ActivatedOffer with the offer model
     * @param activatedOfferEntity
     * @return the activated offer with the offer model
     */
    public ActivatedOffer toActivatedOfferWithOfferModel (ActivatedOfferEntity activatedOfferEntity) {
        return ActivatedOffer.builder()
                .id(activatedOfferEntity.getId())
                .progress(activatedOfferEntity.getProgress())
                .offer(Offer.builder()
                        .id(activatedOfferEntity.getOffer().getId())
                        .name(activatedOfferEntity.getOffer().getName())
                        .description(activatedOfferEntity.getOffer().getDescription())
                        .price(activatedOfferEntity.getOffer().getPrice())
                        .type(activatedOfferEntity.getOffer().getType())
                        .goal(activatedOfferEntity.getOffer().getGoal())
                        .expirationDate(activatedOfferEntity.getOffer().getExpirationDate())
                        .build())
                .build();
    }

    /**
     * Maps a set of ActivatedOfferEntity to a set of ActivatedOffer with the offer model
     * @param activatedOfferEntitySet
     * @return the set of activated offers with the offer model
     */
    public Set<ActivatedOffer> toActivatedOfferWithOfferModelSet (List<ActivatedOfferEntity> activatedOfferEntitySet) {
        return activatedOfferEntitySet
                .stream()
                .map(this::toActivatedOfferWithOfferModel)
                .collect(Collectors.toSet());
    }
}
