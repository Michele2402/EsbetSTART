package unisa.esbetstart.transactionmanagment.presentation.mapper;

import org.springframework.stereotype.Component;
import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
import unisa.esbetstart.transactionmanagment.presentation.response.ActivatedOfferResponse;
import unisa.esbetstart.transactionmanagment.presentation.response.OfferResponse;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PresentationOfferMapper {

    public OfferResponse toResponse(Offer offer) {

        return OfferResponse.builder()
                .id(offer.getId().toString())
                .price(offer.getBonus())
                .description(offer.getDescription())
                .name(offer.getName())
                .expirationDate(offer.getExpirationDate().toString())
                .type(offer.getType().toString())
                .goal(offer.getGoal())
                .build();
    }

    public Set<OfferResponse> toResponseSet(Set<Offer> offers) {
        return offers.stream().map(this::toResponse).collect(Collectors.toSet());
    }

    public ActivatedOfferResponse toActivatedResponse(ActivatedOffer activatedOffer) {

        return ActivatedOfferResponse.builder()
                .id(activatedOffer.getId().toString())
                .description(activatedOffer.getOffer().getDescription())
                .name(activatedOffer.getOffer().getName())
                .type(activatedOffer.getOffer().getType().toString())
                .progress(activatedOffer.getProgress())
                .goal(activatedOffer.getOffer().getGoal())
                .bonus(activatedOffer.getOffer().getBonus())
                .build();
    }

    public LinkedHashSet<ActivatedOfferResponse> toActivatedResponseSet(Set<ActivatedOffer> activatedOffers) {
        return activatedOffers.stream()
                .map(this::toActivatedResponse)
                .sorted(Comparator.comparing(ActivatedOfferResponse::getName))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
