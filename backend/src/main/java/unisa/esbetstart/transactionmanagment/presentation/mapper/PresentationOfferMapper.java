package unisa.esbetstart.transactionmanagment.presentation.mapper;

import org.springframework.stereotype.Component;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
import unisa.esbetstart.transactionmanagment.presentation.response.OfferResponse;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PresentationOfferMapper {

    public OfferResponse toResponse(Offer offer) {

        return OfferResponse.builder()
                .id(offer.getId().toString())
                .price(offer.getPrice())
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
}
