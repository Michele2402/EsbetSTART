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
import unisa.esbetstart.slipmanagment.application.port.out.GetSlipByIdPortOut;
import unisa.esbetstart.slipmanagment.application.port.out.UpdateSlipPortOut;
import unisa.esbetstart.slipmanagment.domain.model.Slip;
import unisa.esbetstart.slipmanagment.presentation.request.UpdateSlipRequest;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateSlipManagerService implements UpdateSlipUseCase {

    private final GetSlipByIdPortOut getSlipByIdPortOut;
    private final ParseAttribute parseAttribute;
    private final GetOddPortOut getOddPortOut;
    private final CheckTypeAttribute checkTypeAttribute;
    private final UpdateSlipPortOut updateSlipPortOut;

    @Override
    public void updateSlip(UpdateSlipRequest request) {

        log.info("Saving slip with id: {}", request.getSlipId());

        //check if the UUID is null or invalid
        UUID id = parseAttribute.checkUUIDIsNullOrInvalid(request.getSlipId(), "Slip Id in save call");

        //check the slip parameters
        Set<Odd> odds = checkUpdateSlipRequest(request);

        //get the slip by id
        Slip slip = getSlipByIdPortOut.getSlipById(id);

        //check if the slip is null
        if (slip == null) {
            throw new ObjectNotFoundException("Slip with id " + request.getSlipId() + " not found");
        }
        
        slip.updateSlip(request.getAmount(), odds);

        //update the slip
        updateSlipPortOut.updateSlip(slip);

        log.info("Slip with id {} saved", request.getSlipId());
    }


    private Set<Odd> checkUpdateSlipRequest(UpdateSlipRequest request) {

        if (request == null) {
            throw new ObjectIsNullException("Request in slip update call can't be null");
        }

        if (request.getOddsIds() == null) {
            throw new ObjectIsNullException("Odds in slip update call can't be null");
        }

        checkTypeAttribute.checkDoubleIsNullOrNegative(request.getAmount(), "Amount in slip update call");


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
