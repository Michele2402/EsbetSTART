package unisa.esbetstart.transactionmanagment.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.transactionmanagment.application.port.out.CreateOfferPortOut;
import unisa.esbetstart.transactionmanagment.application.port.out.GetOfferPortOut;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.OfferEntity;
import unisa.esbetstart.transactionmanagment.infrastructure.mapper.InfrastructureOfferMapper;
import unisa.esbetstart.transactionmanagment.infrastructure.repository.OfferJpaRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferAdapterService implements GetOfferPortOut, CreateOfferPortOut {

        private final OfferJpaRepository offerJpaRepository;

        private final InfrastructureOfferMapper infrastructureOfferMapper;

        /**
        * Gets an offer by its id from the database.
        * @param offerId the id of the offer
        */
        @Override
        public Offer getOfferById(UUID offerId) {
            Optional<OfferEntity> optionalOfferEntity = offerJpaRepository.findById(offerId);

            return optionalOfferEntity
                    .map(infrastructureOfferMapper::toOfferModel)
                    .orElse(null);
        }

        /**
        * Gets an offer by its name from the database.
        * @param offerName the name of the offer
        */
        @Override
        public Offer getOfferByName(String offerName) {

            Optional<OfferEntity> optionalOfferEntity = offerJpaRepository.findByName(offerName);

            return optionalOfferEntity
                    .map(infrastructureOfferMapper::toOfferModel)
                    .orElse(null);
        }

        /**
        * Adds a new offer to the database.
        * @param offer the offer to add
        */
        @Override
        public void addOffer(Offer offer) {
            offerJpaRepository.save(infrastructureOfferMapper.toOfferEntity(offer));
        }

        /**
        * Updates an existing offer in the database.
        * @param offer the offer to update
        */
        @Override
        public void updateOffer(Offer offer) {
            offerJpaRepository.save(infrastructureOfferMapper.toOfferEntity(offer));
        }
}
