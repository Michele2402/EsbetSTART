package unisa.esbetstart.eventmanagement.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.eventmanagement.application.port.in.UpdateOddUseCase;
import unisa.esbetstart.eventmanagement.application.port.out.GetOddPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.UpdateOddPortOut;
import unisa.esbetstart.eventmanagement.domain.model.Odd;
import unisa.esbetstart.eventmanagement.presentation.request.UpdateOddRequest;
import unisa.esbetstart.common.utils.*;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateOddManagerService implements UpdateOddUseCase {

    private final CheckTypeAttribute checkTypeAttribute;
    private final GetOddPortOut getOddPortOut;
    private final UpdateOddPortOut updateOddPortOut;
    private final ParseAttribute parseAttribute;

    @Override
    public void updateOdd(UpdateOddRequest request) {

        log.info("Updating odd with id: {}", request.getOddId());

        //check the id
        UUID oddId = parseAttribute.checkUUIDIsNullOrInvalid(request.getOddId(), "Odd Id in update odd call");

        //check the update odd request
        checkUpdateOddRequest(request);

        //get the odd
        Odd oddToUpdate = getOddPortOut.getOddByIdWithSimpleDetails(oddId);

        //check if the odd exists
        if (oddToUpdate == null) {
            log.error("Odd with id {} not found", oddId.toString());
            throw new ObjectIsNullException("Odd with id " + oddId + " not found");
        }

        //update the odd model
        oddToUpdate.updateOdd(request.getOddValue());

        //update the odd
        updateOddPortOut.updateOdd(oddToUpdate);

        log.info("Odd with id {} updated", oddId.toString());

    }

    private void checkUpdateOddRequest(UpdateOddRequest request) {

        checkTypeAttribute.checkFloatIsNullOrSmallerThan(request.getOddValue(), 1.0F, "Odd value in update odd call");

    }

}
