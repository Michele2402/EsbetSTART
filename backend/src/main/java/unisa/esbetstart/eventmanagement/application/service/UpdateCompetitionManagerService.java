package unisa.esbetstart.eventmanagement.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.eventmanagement.application.port.in.UpdateCompetitionUseCase;
import unisa.esbetstart.eventmanagement.application.port.out.GetCompetitionPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.UpdateCompetitionPortOut;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.presentation.request.UpdateCompetitionRequest;
import unisa.esbetstart.common.utils.*;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateCompetitionManagerService implements UpdateCompetitionUseCase {

    private final CheckTypeAttribute checkTypeAttribute;
    private final UpdateCompetitionPortOut updateCompetitionPortOut;
    private final GetCompetitionPortOut getCompetitionPortOut;
    private final ParseAttribute parseAttribute;

    @Override
    public void updateCompetition(UpdateCompetitionRequest request) {

        log.info("Updating competition with id: {}", request.getCompetitionId());

        //check and get competition id
        UUID competitionId = parseAttribute.checkUUIDIsNullOrInvalid(request.getCompetitionId(), "Competition Id in update call");

        //check the update competition request
        checkUpdateCompetitionRequest(request);

        //get the competition
        Competition competitionToUpdate = getCompetitionPortOut.getCompetitionByIdWithSimpleGame(competitionId);

        //check if the competition exists
        if (competitionToUpdate == null) {
            log.error("Competition with id {} not found", competitionId.toString());
            throw new ObjectIsNullException("Competition with id " + competitionId + " not found");
        }

        competitionToUpdate.updateCompetition(request.getName(), request.getOriginCountry());

        //update the competition
        updateCompetitionPortOut.updateCompetition(competitionToUpdate);

        log.info("Competition with id {} updated", competitionId.toString());
    }

    private void checkUpdateCompetitionRequest(UpdateCompetitionRequest request) {

        if (request == null) {
            log.error("Update competition request is null");
            throw new ObjectIsNullException("Update competition request is null");
        }

        checkTypeAttribute.checkStringIsNullOrEmpty(request.getName(), "Name in update competition request");
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getOriginCountry(), "Origin country in update competition request");
    }


}
