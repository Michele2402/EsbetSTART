package unisa.esbetstart.transactionmanagment.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.transactionmanagment.application.port.in.*;
import unisa.esbetstart.transactionmanagment.presentation.request.AddOfferRequest;
import unisa.esbetstart.transactionmanagment.presentation.request.UpdateOfferRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/offers")
@CrossOrigin("*")
public class OfferController {

    private final CreateOfferUseCase createOfferUseCase;
    private final UpdateOfferUseCase updateOfferUseCase;
    private final RemoveOfferUseCase removeOfferUseCase;

    /**
     * Adds a new offer to the database.
     * @param request the AddOfferRequest containing the offer data
     */
    @PostMapping("/add")
    public void addOffer(
            @RequestBody AddOfferRequest request
    ) {
        createOfferUseCase.createOffer(request);
    }

    /**
     * Updates an offer in the database.
     * @param request the UpdateOfferRequest containing the offer data
     */
    @PostMapping("/update")
    public void updateOffer(
            @RequestBody UpdateOfferRequest request
    ) {
        updateOfferUseCase.updateOffer(request);
    }

    /**
     * Removes an offer from the database.
     * @param offerId the id of the offer
     */
    @PostMapping("/remove")
    public void removeOffer(
            @RequestBody String offerId
    ) {
        removeOfferUseCase.removeOffer(offerId);
    }
}
