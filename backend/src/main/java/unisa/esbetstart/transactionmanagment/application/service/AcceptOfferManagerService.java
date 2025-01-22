package unisa.esbetstart.transactionmanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.transactionmanagment.application.port.in.AcceptOfferUseCase;
import unisa.esbetstart.transactionmanagment.application.port.out.GetOfferPortOut;
import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.ActivatedOfferEntity;
import unisa.esbetstart.transactionmanagment.infrastructure.mapper.InfrastructureOfferMapper;
import unisa.esbetstart.transactionmanagment.infrastructure.repository.ActivatedOfferJpaRepository;
import unisa.esbetstart.transactionmanagment.presentation.request.AcceptOfferRequest;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.application.port.out.GetUserPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AcceptOfferManagerService implements AcceptOfferUseCase {

    private final ParseAttribute parseAttribute;
    private final GetOfferPortOut getOfferPortOut;
    private final GetGamblerPortOut getGamblerPortOut;
    private final CheckTypeAttribute CheckTypeAttribute;
    private final InfrastructureOfferMapper infrastructureOfferMapper;
    private final ActivatedOfferJpaRepository activatedOfferRepository;

    @Override
    public void acceptOffer(AcceptOfferRequest request) {
        log.info("Accepting offer: {}", request.getOfferId());

        // Check if the offer ID is valid
        UUID offerId = parseAttribute.checkUUIDIsNullOrInvalid(request.getOfferId(), "Offer ID");

        // Check if the gambler email is valid
        CheckTypeAttribute.checkStringIsNullOrEmpty(request.getGamblerEmail(), "Gambler Email");

        // Get the offer and the gambler
        Offer offer = getOfferPortOut.getOfferById(offerId);
        Gambler gambler = getGamblerPortOut.getGamblerByEmail(request.getGamblerEmail());

        if(offer == null) {
            log.error("Offer not found");
            throw new ObjectNotFoundException("Offer not found");
        }

        //TODO la funzione acceptOffer deve controllare che il gambler non abbia un offerta attiva che abbia lo stesso
        // Offer id dell'offer passato in input.
        // Quindi gambler si deve mappare tutte le activatedOfferEntity con entityGraph e modificare il suo metodo interno

        if(gambler == null) {
            log.error("Gambler not found");
            throw new ObjectNotFoundException("Gambler not found");
        }

        // Accept the offer
        ActivatedOffer activatedOffer = gambler.acceptOffer(offer);

        // Map the activated offer to an entity
        ActivatedOfferEntity activatedOfferEntity = infrastructureOfferMapper.toActivatedOfferEntity(activatedOffer);

        // Save the activated offer
        activatedOfferRepository.save(activatedOfferEntity);

        log.info("Offer accepted");
    }

}
