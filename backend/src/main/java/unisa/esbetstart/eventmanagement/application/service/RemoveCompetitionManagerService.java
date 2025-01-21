package unisa.esbetstart.eventmanagement.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.eventmanagement.application.port.out.GetCompetitionPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.RemoveCompetitionPortOut;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.usermanagment.application.utils.CheckTypeAttribute;
//fix all the imports
import unisa.esbetstart.eventmanagement.application.port.in.RemoveCompetitionUseCase;



import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RemoveCompetitionManagerService implements RemoveCompetitionUseCase {

    private final CheckTypeAttribute checkTypeAttribute;
    private final RemoveCompetitionPortOut removeCompetitionPortOut;
    private final GetCompetitionPortOut getCompetitionPortOut;

    @Override
    public void removeCompetition(String competitionId) {
        log.info("Removing competition with id: {}", competitionId);

        // Check if competitionId is null or invalid
        UUID id = checkTypeAttribute.checkUUIDIsNullOrInvalid(competitionId, "Competition Id in remove call");

        // Check if the competition exists and has no events that are not ended
        Competition competition = getCompetitionPortOut.getCompetitionByIdWithEventsList(id);

        if (competition == null || competition.hasNotEndedEvents()) {
            throw new ObjectIsNullException("Competition with id " + competitionId + " not found or has not ended events");
        }

        // Remove the competition
        removeCompetitionPortOut.removeCompetition(id);

        log.info("Competition with id {} removed", competitionId);
    }
}
