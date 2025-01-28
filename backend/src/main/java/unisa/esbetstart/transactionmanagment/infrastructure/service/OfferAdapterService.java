package unisa.esbetstart.transactionmanagment.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.transactionmanagment.application.port.out.*;
import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.ActivatedOfferEntity;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.OfferEntity;
import unisa.esbetstart.transactionmanagment.infrastructure.mapper.InfrastructureOfferMapper;
import unisa.esbetstart.transactionmanagment.infrastructure.repository.ActivatedOfferJpaRepository;
import unisa.esbetstart.transactionmanagment.infrastructure.repository.OfferJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferAdapterService implements GetOfferPortOut, CreateOfferPortOut, RemoveOfferPortOut, CreateActivatedOfferPortOut, DeleteActivatedOfferPortOut {

    private final OfferJpaRepository offerJpaRepository;

    private final ActivatedOfferJpaRepository activatedOfferJpaRepository;

    private final InfrastructureOfferMapper infrastructureOfferMapper;

    /**
     * Gets an offer by its id from the database.
     *
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
     *
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
     * Gets all offers from the database.
     */
    @Override
    public Set<Offer> getAllOffers() {

        List<OfferEntity> offerEntities = offerJpaRepository.findAll();

        return infrastructureOfferMapper.toOfferModelSet(offerEntities);
    }

    @Override
    public Set<ActivatedOffer> getActivatedOffersByGamblerEmail(String gamblerEmail) {
        List<ActivatedOfferEntity> activatedOffers = activatedOfferJpaRepository
                .findAllByGamblerEmailWithOffers(gamblerEmail);

        return infrastructureOfferMapper.toActivatedOfferWithOfferModelSet(activatedOffers);
    }

    /**
     * Adds a new offer to the database.
     *
     * @param offer the offer to add
     */
    @Override
    public void addOffer(Offer offer) {
        offerJpaRepository.save(infrastructureOfferMapper.toOfferEntity(offer));
    }

    /**
     * Updates an existing offer in the database.
     *
     * @param offer the offer to update
     */
    @Override
    public void updateOffer(Offer offer) {
        offerJpaRepository.save(infrastructureOfferMapper.toOfferEntity(offer));
    }

    /**
     * Removes an offer from the database.
     *
     * @param offerId the id of the offer
     */
    @Override
    public void removeOffer(UUID offerId) {
        offerJpaRepository.deleteById(offerId);
    }

    /**
     * Adds an accepted offer to the database.
     *
     * @param offer the offer to add
     */
    @Override
    public void addAcceptedOffer(ActivatedOffer offer) {
        activatedOfferJpaRepository.save(infrastructureOfferMapper.toActivatedOfferEntity(offer));
    }

    /**
     * Deletes an activated offer by its id from the database.
     *
     * @param activatedOfferId the id of the activated offer
     */
    @Override
    public void deleteActivatedOfferById(UUID activatedOfferId) {
        activatedOfferJpaRepository.deleteById(activatedOfferId);
    }
}
