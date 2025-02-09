package unisa.esbetstart.eventmanagement.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.DomainAttributeException;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.common.exceptions.SizeMismatchException;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.eventmanagement.application.mapper.ApplicationOddMapper;
import unisa.esbetstart.eventmanagement.application.port.in.CreateEventUseCase;
import unisa.esbetstart.eventmanagement.application.port.out.CreateEventPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetCompetitionPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetGamePortOut;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.domain.model.Event;
import unisa.esbetstart.eventmanagement.domain.model.Rule;
import unisa.esbetstart.eventmanagement.presentation.request.AddEventRequest;
import unisa.esbetstart.eventmanagement.presentation.request.AddOddRequest;
import unisa.esbetstart.common.utils.CheckTypeAttribute;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateEventManagerService implements CreateEventUseCase {

    private final CheckTypeAttribute checkTypeAttribute;
    private final ParseAttribute parseAttribute;
    private final GetCompetitionPortOut getCompetitionPortOut;
    private final GetGamePortOut getGamePortOut;
    private final ApplicationOddMapper applicationOddMapper;
    private final CreateEventPortOut createEventPortOut;

    @Override
    public void createEvent(AddEventRequest request) {
        if (request == null) {
            log.error("Event request is null");
            throw new ObjectIsNullException("Event request is null");
        }

        log.info("Adding event {} to database", request.getName());

        //check and get competition id
        UUID competitionId = parseAttribute.checkUUIDIsNullOrInvalid(request.getCompetitionId(), "Competition Id in event");

        //check if the date format is correct
        LocalDateTime date = parseAttribute.convertStringIntoDate(request.getDate(), "Date in event");

        //get competition
        Competition competition = getCompetitionPortOut.getCompetitionByIdWithRules(competitionId);

        //check if the competition exists
        if (competition == null) {
            log.error("Competition with id {} not found", competitionId);
            throw new ObjectNotFoundException("Competition with id " + competitionId + " not found");
        }

        //get rules
        Set<Rule> rules = competition.getGame().getRules();

        //check the event request
        checkAddEventRequest(request, rules, date);

        //build the event object
        Event event = Event.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .date(date)
                .isEnded(false)
                .odds(request.getOdds().stream().map(applicationOddMapper::toOddModel).collect(Collectors.toSet()))
                .build();

        //add the event to the competition
        competition.addEvent(event);

        //set the event to the odds
        event.getOdds().forEach(odd -> odd.setEvent(event));

        //add the event to the database
        createEventPortOut.addEvent(event);

        log.info("Event {} added to database", request.getName());

    }

    /**
     * Checks the AddEventRequest object.
     * @param request the AddEventRequest object to check
     */
    public void checkAddEventRequest(AddEventRequest request, Set<Rule> rules, LocalDateTime date) {
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getName(), "Name of the event");
        checkTypeAttribute.checkStringIsLessThan(request.getName(), 30, "Name of the event");
        checkTypeAttribute.checkIfDateIsNullOrInThePast(date, "Date of the event");

        if(rules.size() != request.getOdds().size()) {
            log.error("The number of rules and odds must be the same");
            throw new SizeMismatchException("The number of rules and odds must be the same");
        }

        request.getOdds().forEach(this::checkAddOddRequest);
        checkRuleMatch(request.getOdds(), rules);
    }

    /**
     * Checks the AddOddRequest object.
     * @param request the AddOddRequest object to check
     */
    public void checkAddOddRequest(AddOddRequest request) {

        //null check
        if(request == null){
            log.error("Odd request is null");
            throw new ObjectIsNullException("Odd request is null");
        }
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getName(), "Odd name");
        checkTypeAttribute.checkFloatIsNullOrNegative(request.getValue(), "Odd value");
    }

    /**
     * Checks if the rules match the odds. Sets the odds to the rules position
     * @param request the list of AddOddRequest objects
     * @param rules the set of Rule objects
     */
    public void checkRuleMatch(List<AddOddRequest> request, Set<Rule> rules) {

        if(request.stream().map(AddOddRequest::getName).distinct().count() != request.size()) {
            log.error("The odd names must be unique");
            throw new DomainAttributeException("The odd names must be unique");
        }

        for (AddOddRequest odd : request) {
            if (rules.stream().noneMatch(rule -> rule.getName().equals(odd.getName()))) {
                log.error("Rule with name {} not found", odd.getName());
                throw new ObjectNotFoundException("Rule with name " + odd.getName() + " not found");
            }
        }

        //for each request set the position parameter to be equal to that in the rule
        for (AddOddRequest odd : request) {
            rules.stream()
                    .filter(rule -> rule.getName().equals(odd.getName()))
                    .findFirst()
                    .ifPresent(rule -> odd.setPosition(rule.getPosition()));
        }

    }
}
