package unisa.esbetstart.slipmanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.eventmanagement.application.port.out.GetOddPortOut;
import unisa.esbetstart.eventmanagement.domain.model.Odd;
import unisa.esbetstart.slipmanagment.application.port.in.UpdateSlipUseCase;
import unisa.esbetstart.slipmanagment.application.port.out.GetSlipPortOut;
import unisa.esbetstart.slipmanagment.application.port.out.UpdateSlipPortOut;
import unisa.esbetstart.slipmanagment.domain.model.Slip;
import unisa.esbetstart.slipmanagment.presentation.request.UpdateSlipRequest;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateSlipManagerService implements UpdateSlipUseCase {

    private final GetSlipPortOut getSlipByIdPortOut;
    private final ParseAttribute parseAttribute;
    private final GetOddPortOut getOddPortOut;
    private final CheckTypeAttribute checkTypeAttribute;
    private final UpdateSlipPortOut updateSlipPortOut;
    private final GetGamblerPortOut getGamblerPortOut;

    @Override
    public void updateSlip(UpdateSlipRequest request) {

        //get the gambler by email
        Gambler gambler = getGamblerPortOut.getGamblerByEmailWithSlip(request.getGamblerEmail());

        //check if the gambler is null
        if(gambler == null){
            throw new ObjectNotFoundException("Gambler with email " + request.getGamblerEmail() + " not found");
        }

        log.info("Saving slip with id: {}", gambler.getSlip().getId());

        //check the slip parameters
        Set<Odd> odds = checkUpdateSlipRequest(request);

        //get the slip by id
        Slip slip = getSlipByIdPortOut.getSlipById(gambler.getSlip().getId());

        //check if the slip is null
        if (slip == null) {
            throw new ObjectNotFoundException("Slip with id " + gambler.getSlip().getId() + " not found");
        }
        
        slip.updateSlip(request.getAmount(), odds);

        //update the slip
        updateSlipPortOut.updateSlip(slip);

        log.info("Slip with id {} saved", gambler.getSlip().getId());
    }


    private Set<Odd> checkUpdateSlipRequest(UpdateSlipRequest request) {

        if (request == null) {
            throw new ObjectIsNullException("Request in slip update call can't be null");
        }

        if (request.getOddsIds() == null) {
            throw new ObjectIsNullException("Odds in slip update call can't be null");
        }

        checkTypeAttribute.checkDoubleIsNullOrNegative(request.getAmount(), "Amount in slip update call");

        //if the odds are empty, the slip must be flushed
        if (request.getOddsIds().isEmpty()) {
            return new HashSet<Odd>();
        }

        Set<Odd> odds = request.getOddsIds()
                .stream()
                .map(odd ->
                        getOddPortOut.getOddByIdWithSimpleDetails(parseAttribute.checkUUIDIsNullOrInvalid(odd, "Odd id in slip update call")))
                .collect(Collectors.toSet());


        odds.forEach(odd -> {
            if (odd == null) {
                throw new ObjectNotFoundException("One or more odds not found in slip update call");
            }
        });

        return odds;
    }

}
