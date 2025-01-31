package unisa.esbetstart.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import unisa.esbetstart.common.exceptions.*;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.eventmanagement.application.mapper.ApplicationOddMapper;
import unisa.esbetstart.eventmanagement.application.port.out.CreateEventPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetCompetitionPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetGamePortOut;
import unisa.esbetstart.eventmanagement.application.service.CreateEventManagerService;
import unisa.esbetstart.eventmanagement.domain.model.*;
import unisa.esbetstart.eventmanagement.presentation.request.AddEventRequest;
import unisa.esbetstart.eventmanagement.presentation.request.AddOddRequest;
import unisa.esbetstart.common.utils.ParseAttribute;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateEventManagerServiceUnitTest {

    @Mock
    private CheckTypeAttribute checkTypeAttribute;

    @Mock
    private ParseAttribute parseAttribute;

    @Mock
    private GetCompetitionPortOut getCompetitionPortOut;

    @Mock
    private GetGamePortOut getGamePortOut;

    @Mock
    private ApplicationOddMapper applicationOddMapper;

    @Mock
    private CreateEventPortOut createEventPortOut;

    @InjectMocks
    private CreateEventManagerService createEventManagerService;

    private AddEventRequest validEventRequest;
    private AddOddRequest validOddRequest;
    private Competition competition;
    private Rule rule;

    @BeforeEach
    void setUp() {
        validOddRequest = new AddOddRequest();
        validOddRequest.setName("Odd 1");
        validOddRequest.setValue(1.5f);

        validEventRequest = new AddEventRequest();
        validEventRequest.setName("Test Event");
        validEventRequest.setCompetitionId(UUID.randomUUID().toString());
        validEventRequest.setDate("2023-12-31T23:59:59");
        validEventRequest.setOdds(List.of(validOddRequest));

        rule = new Rule();
        rule.setName("Odd 1");
        rule.setPosition(1);

        Game game = new Game();
        game.setRules(Set.of(rule));

        competition = new Competition();
        competition.setId(UUID.randomUUID());
        competition.setGame(game);
    }

    @Test
    void createEvent_ValidRequest_Success() {
        when(parseAttribute.checkUUIDIsNullOrInvalid(validEventRequest.getCompetitionId(), "Competition Id in event"))
                .thenReturn(UUID.fromString(validEventRequest.getCompetitionId()));
        when(parseAttribute.convertStringIntoDate(validEventRequest.getDate(), "Date in event"))
                .thenReturn(LocalDateTime.parse(validEventRequest.getDate()));

        Competition competition = new Competition();
        competition.setId(UUID.randomUUID());
        competition.setEvents(new HashSet<>());

        Game game = new Game();
        game.setRules(Set.of(rule));
        competition.setGame(game);

        when(getCompetitionPortOut.getCompetitionByIdWithRules(any(UUID.class))).thenReturn(competition);

        Odd mockOdd = mock(Odd.class);
        when(applicationOddMapper.toOddModel(any(AddOddRequest.class))).thenReturn(mockOdd);

        createEventManagerService.createEvent(validEventRequest);

        verify(createEventPortOut, times(1)).addEvent(any(Event.class));
        assertEquals(1, competition.getEvents().size());
    }

    @Test
    void createEvent_NullRequest_ThrowsException() {
        ObjectIsNullException exception = assertThrows(ObjectIsNullException.class, () -> {
            createEventManagerService.createEvent(null);
        });

        assertEquals("Event request is null", exception.getMessage());
    }

    @Test
    void createEvent_CompetitionNotFound_ThrowsException() {
        when(parseAttribute.checkUUIDIsNullOrInvalid(validEventRequest.getCompetitionId(), "Competition Id in event"))
                .thenReturn(UUID.fromString(validEventRequest.getCompetitionId()));

        when(getCompetitionPortOut.getCompetitionByIdWithRules(any(UUID.class))).thenReturn(null);

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            createEventManagerService.createEvent(validEventRequest);
        });

        assertEquals("Competition with id " + validEventRequest.getCompetitionId() + " not found", exception.getMessage());
    }

    @Test
    void createEvent_SizeMismatch_ThrowsException() {
        validEventRequest.setOdds(List.of(validOddRequest, validOddRequest));

        when(parseAttribute.checkUUIDIsNullOrInvalid(validEventRequest.getCompetitionId(), "Competition Id in event"))
                .thenReturn(UUID.fromString(validEventRequest.getCompetitionId()));
        when(parseAttribute.convertStringIntoDate(validEventRequest.getDate(), "Date in event"))
                .thenReturn(LocalDateTime.parse(validEventRequest.getDate()));

        when(getCompetitionPortOut.getCompetitionByIdWithRules(any(UUID.class))).thenReturn(competition);

        Exception exception = assertThrows(SizeMismatchException.class, () -> {
            createEventManagerService.createEvent(validEventRequest);
        });

        assertEquals("The number of rules and odds must be the same", exception.getMessage());
    }

    @Test
    void checkAddEventRequest_ValidRequest_NoExceptionThrown() {
        assertDoesNotThrow(() -> createEventManagerService.checkAddEventRequest(validEventRequest, competition.getGame().getRules(), LocalDateTime.now()));
    }

    @Test
    void checkAddOddRequest_ValidRequest_NoExceptionThrown() {
        assertDoesNotThrow(() -> createEventManagerService.checkAddOddRequest(validOddRequest));
    }

    @Test
    void checkRuleMatch_ValidRequest_NoExceptionThrown() {
        assertDoesNotThrow(() -> createEventManagerService.checkRuleMatch(List.of(validOddRequest), competition.getGame().getRules()));
    }
}