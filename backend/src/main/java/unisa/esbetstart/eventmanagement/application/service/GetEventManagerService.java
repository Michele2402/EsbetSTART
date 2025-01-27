package unisa.esbetstart.eventmanagement.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.eventmanagement.application.port.in.GetEventUseCase;
import unisa.esbetstart.eventmanagement.application.port.out.GetCompetitionPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetEventPortOut;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.domain.model.Event;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetEventManagerService implements GetEventUseCase {

    private final GetCompetitionPortOut getCompetitionPortOut;
    private final GetEventPortOut getEventPortOut;

    private final ParseAttribute parseAttribute;

    /**
     * Gets a list of events by their competition ID
     * @param competitionId the ID of the competition
     * @return the list of events
     */
    @Override
    public List<Event> getAllByCompetitionId(String competitionId) {

        log.info("Get all events by competition id: " + competitionId);

        UUID competitionIdUUID = parseAttribute.checkUUIDIsNullOrInvalid(competitionId, "competition Id");

        Competition competition = getCompetitionPortOut.getCompetitionByIdWithSimpleGame(competitionIdUUID);

        if(competition == null) {
            log.error("Competition not found");
            throw new ObjectNotFoundException("Competition not found");
        }

        List<Event> events = getEventPortOut.getAllByCompetitionId(competitionIdUUID);

        log.info("All events retrieved by competition id: " + competitionId);

        return events;
    }
}
