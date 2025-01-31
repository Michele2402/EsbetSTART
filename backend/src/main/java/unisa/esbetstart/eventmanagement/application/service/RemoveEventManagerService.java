// RemoveEventManagerService.java
package unisa.esbetstart.eventmanagement.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.eventmanagement.application.port.in.RemoveEventUseCase;
import unisa.esbetstart.eventmanagement.application.port.out.GetEventPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.RemoveEventPortOut;
import unisa.esbetstart.eventmanagement.domain.model.Event;
import unisa.esbetstart.common.utils.*;
import unisa.esbetstart.eventmanagement.presentation.request.EndEventRequest;
import unisa.esbetstart.slipmanagment.application.port.out.GetOddStaticPortOut;
import unisa.esbetstart.slipmanagment.application.port.out.UpdateBetPlacedPortOut;
import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;
import unisa.esbetstart.usermanagment.application.port.out.UpdateUserPortOut;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RemoveEventManagerService implements RemoveEventUseCase {

    private final RemoveEventPortOut removeEventPortOut;
    private final GetEventPortOut getEventPortOut;
    private final ParseAttribute parseAttribute;
    private final GetOddStaticPortOut getOddStaticPortOut;
    private final UpdateBetPlacedPortOut updateBetPlacedPortOut;
    private final UpdateUserPortOut updateUserPortOut;

    @Override
    public void removeEvent(String eventId) {
        log.info("Removing event with id: {}", eventId);

        // Check if eventId is null or invalid
        UUID id = parseAttribute.checkUUIDIsNullOrInvalid(eventId, "Event Id in remove call");

        // Check if the event exists and is ended
        Event event = getEventPortOut.getEventByIdWithoutOdds(id);

        if (event == null || !event.isEnded()) {
            throw new ObjectNotFoundException("Event with id " + eventId + " not found or not ended");
        }

        // Remove the event
        removeEventPortOut.removeEvent(id);

        log.info("Event with id {} removed", eventId);
    }

    @Override
    public void endEvent(EndEventRequest event) {

        log.info("Ending event with id: {}", event);

        // Check if eventId is null or invalid
        UUID id = parseAttribute.checkUUIDIsNullOrInvalid(event.getEventId(), "Event Id in end call");

        // Check if the event exists and is not ended
        Event eventToBeEnded = getEventPortOut.getEventByIdToBetPlaced(id);

        if (eventToBeEnded == null || eventToBeEnded.isEnded()) {
            throw new ObjectNotFoundException("Event with id " + id + " not found or already ended");
        }

        //get the ids of the winning odds
        List<UUID> winningOdds = event.getWinningOdds()
                .stream()
                .map(odd -> parseAttribute.checkUUIDIsNullOrInvalid(odd, "Winning Odd Id"))
                .collect(Collectors.toList());

        //check if the list is empty
        if(winningOdds.isEmpty()){
            throw new ObjectNotFoundException("Winning odds list can't be empty");
        }

        // End the event
        List<UUID> oddStaticsModified =  eventToBeEnded.endEvent(winningOdds);

        // end the event in the database
        removeEventPortOut.endEvent(eventToBeEnded);

        // check for each oddStatic if the bet is won and update the balance of the gambler
        oddStaticsModified.forEach(oddStaticId -> {

            BetPlaced betPlaced = getOddStaticPortOut.getOddStaticToGamblerById(oddStaticId).getBetPlaced();
            double amount = betPlaced.evaluateResult();
            if(amount > 0) {
                log.info("Gambler with email {} won {} on odd with id {}", betPlaced.getGambler().getEmail(), amount, oddStaticId);
                betPlaced.getGambler().setWithdrawableBalance(betPlaced.getGambler().getWithdrawableBalance() + amount);
            }

            // update the bet placed
            updateBetPlacedPortOut.updateBetPlaced(betPlaced);
            updateUserPortOut.updateWithdrawableBalance(betPlaced.getGambler().getEmail(), betPlaced.getGambler().getWithdrawableBalance());

        });

        log.info("Event with id {} ended", id);


    }
}