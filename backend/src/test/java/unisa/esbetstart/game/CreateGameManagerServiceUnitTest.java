package unisa.esbetstart.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.eventmanagement.application.mapper.ApplicationRuleMapper;
import unisa.esbetstart.eventmanagement.application.port.out.CreateGamePortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetGamePortOut;
import unisa.esbetstart.eventmanagement.application.service.CreateGameManagerService;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.domain.model.Rule;
import unisa.esbetstart.eventmanagement.presentation.request.AddGameRequest;
import unisa.esbetstart.eventmanagement.presentation.request.AddRuleRequest;
import unisa.esbetstart.common.utils.CheckTypeAttribute;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateGameManagerServiceUnitTest {

    @Mock
    private CheckTypeAttribute checkTypeAttribute;

    @Mock
    private ApplicationRuleMapper applicationRuleMapper;

    @Mock
    private GetGamePortOut getGamePortOut;

    @Mock
    private CreateGamePortOut createGamePortOut;

    @InjectMocks
    private CreateGameManagerService createGameManagerService;

    private AddGameRequest validGameRequest;
    private AddRuleRequest validRuleRequest;

    @BeforeEach
    void setUp() {
        validRuleRequest = new AddRuleRequest();
        validRuleRequest.setName("Rule 1");
        validRuleRequest.setPosition(1);

        validGameRequest = new AddGameRequest();
        validGameRequest.setName("Test Game");
        validGameRequest.setRules(Collections.singletonList(validRuleRequest));
    }

    @Test
    void createGame_ValidRequest_Success() {
        when(getGamePortOut.getGameByName(validGameRequest.getName())).thenReturn(null);

        Rule mockRule = mock(Rule.class);
        when(applicationRuleMapper.toRuleModel(any(AddRuleRequest.class))).thenReturn(mockRule);

        createGameManagerService.createGame(validGameRequest);
        verify(createGamePortOut, times(1)).addGame(any(Game.class));

        verify(mockRule, times(1)).setGame(any(Game.class));
    }

    @Test
    void createGame_GameAlreadyExists_ThrowsException() {
        when(getGamePortOut.getGameByName(validGameRequest.getName())).thenReturn(new Game());

        ObjectIsNullException exception = assertThrows(ObjectIsNullException.class, () -> {
            createGameManagerService.createGame(validGameRequest);
        });

        assertEquals("Game with name " + validGameRequest.getName() + " already exists", exception.getMessage());
    }

    @Test
    void createGame_NullRequest_ThrowsException() {
        ObjectIsNullException exception = assertThrows(ObjectIsNullException.class, () -> {
            createGameManagerService.createGame(null);
        });

        assertEquals("AddGameRequest is null", exception.getMessage());
    }

    @Test
    void createGame_EmptyRules_ThrowsException() {
        validGameRequest.setRules(Collections.emptyList());

        Exception exception = assertThrows(ObjectIsNullException.class, () -> {
            createGameManagerService.createGame(validGameRequest);
        });

        assertEquals("AddGameRequest has no rules", exception.getMessage());
    }

    @Test
    void checkAddGameRequest_ValidRequest_NoExceptionThrown() {
        assertDoesNotThrow(() -> createGameManagerService.checkAddGameRequest(validGameRequest));
    }

    @Test
    void checkAddGameRequest_NullRequest_ThrowsException() {
        Exception exception = assertThrows(ObjectIsNullException.class, () -> {
            createGameManagerService.checkAddGameRequest(null);
        });

        assertEquals("AddGameRequest is null", exception.getMessage());
    }

    @Test
    void checkAddRuleRequest_ValidRequest_NoExceptionThrown() {
        assertDoesNotThrow(() -> createGameManagerService.checkAddRuleRequest(validRuleRequest));
    }

    @Test
    void checkAddRuleRequest_NullRequest_ThrowsException() {
        Exception exception = assertThrows(ObjectIsNullException.class, () -> {
            createGameManagerService.checkAddRuleRequest(null);
        });

        assertEquals("AddRuleRequest is null", exception.getMessage());
    }
}