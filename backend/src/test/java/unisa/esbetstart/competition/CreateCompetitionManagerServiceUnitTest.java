package unisa.esbetstart.competition;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.eventmanagement.application.port.out.CreateCompetitionPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetGamePortOut;
import unisa.esbetstart.eventmanagement.application.service.CreateCompetitionManagerService;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.presentation.request.AddCompetitionRequest;

import java.util.HashSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCompetitionManagerServiceUnitTest {

    @Mock
    private CheckTypeAttribute checkTypeAttribute;

    @Mock
    private ParseAttribute parseAttribute;

    @Mock
    private GetGamePortOut getGamePortOut;

    @Mock
    private CreateCompetitionPortOut createCompetitionPortOut;

    @InjectMocks
    private CreateCompetitionManagerService createCompetitionManagerService;

    private AddCompetitionRequest validRequest;
    private Game game;

    @BeforeEach
    void setUp() {
        validRequest = new AddCompetitionRequest();
        validRequest.setName("Test Competition");
        validRequest.setOriginCountry("Test Country");
        validRequest.setGameId(UUID.randomUUID().toString());

        game = new Game();
        game.setId(UUID.randomUUID());
        game.setName("Test Game");
        game.setCompetitions(new HashSet<>());
    }
    @Test
    void addCompetition_ValidRequest_Success() {
        when(parseAttribute.checkUUIDIsNullOrInvalid(validRequest.getGameId(), "Game Id"))
                .thenReturn(UUID.fromString(validRequest.getGameId()));

        when(getGamePortOut.getGameById(any(UUID.class))).thenReturn(game);

        createCompetitionManagerService.addCompetition(validRequest);

        verify(createCompetitionPortOut, times(1)).addCompetition(any(Competition.class));

        assertEquals(1, game.getCompetitions().size());
    }

    @Test
    void addCompetition_NullRequest_ThrowsException() {
        ObjectIsNullException exception = assertThrows(ObjectIsNullException.class, () -> {
            createCompetitionManagerService.addCompetition(null);
        });

        assertEquals("AddCompetitionRequest is null", exception.getMessage());
    }

    @Test
    void addCompetition_GameNotFound_ThrowsException() {
        when(parseAttribute.checkUUIDIsNullOrInvalid(validRequest.getGameId(), "Game Id"))
                .thenReturn(UUID.fromString(validRequest.getGameId()));

        when(getGamePortOut.getGameById(any(UUID.class))).thenReturn(null);

        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            createCompetitionManagerService.addCompetition(validRequest);
        });

        assertEquals("Game with id " + validRequest.getGameId() + " not found", exception.getMessage());
    }

    @Test
    void checkAddCompetitionRequest_ValidRequest_NoExceptionThrown() {
        assertDoesNotThrow(() -> createCompetitionManagerService.checkAddCompetitionRequest(validRequest));
    }

    @Test
    void checkAddCompetitionRequest_NullRequest_ThrowsException() {
        Exception exception = assertThrows(ObjectIsNullException.class, () -> {
            createCompetitionManagerService.checkAddCompetitionRequest(null);
        });

        assertEquals("AddCompetitionRequest is null", exception.getMessage());
    }
}