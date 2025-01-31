package unisa.esbetstart.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import unisa.esbetstart.eventmanagement.application.port.out.GetGamePortOut;
import unisa.esbetstart.eventmanagement.application.service.GetGameManagerService;
import unisa.esbetstart.eventmanagement.domain.model.Game;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetGameManagerServiceUnitTest {

    @Mock
    private GetGamePortOut getGamePortOut;

    @InjectMocks
    private GetGameManagerService getGameManagerService;

    private Game game;

    @BeforeEach
    void setUp() {
        // Inizializza un oggetto Game per i test
        game = new Game();
        game.setId(UUID.randomUUID());
        game.setName("Test Game");
    }

    @Test
    void getAllGames_Success() {
        when(getGamePortOut.getAllGames()).thenReturn(Collections.singleton(game));

        Set<Game> result = getGameManagerService.getAllGames();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.contains(game));

        verify(getGamePortOut, times(1)).getAllGames();
    }

    @Test
    void getFiltered_Success() {
        String name = "Test";
        int size = 10;
        int page = 0;

        Page<Game> mockPage = new PageImpl<>(Collections.singletonList(game));

        when(getGamePortOut.getFiltered(eq(name), any(PageRequest.class))).thenReturn(mockPage);

        Page<Game> result = getGameManagerService.getFiltered(name, size, page);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertTrue(result.getContent().contains(game));

        verify(getGamePortOut, times(1)).getFiltered(eq(name), any(PageRequest.class));
    }
}