package unisa.esbetstart.eventmanagement.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.eventmanagement.application.port.in.CreateGameUseCase;
import unisa.esbetstart.eventmanagement.application.port.in.RemoveGameUseCase;
import unisa.esbetstart.eventmanagement.application.port.in.UpdateGameUseCase;
import unisa.esbetstart.eventmanagement.presentation.request.AddGameRequest;
import unisa.esbetstart.eventmanagement.presentation.request.UpdateGameRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/games")
@CrossOrigin("*")
public class GameController {

    private final CreateGameUseCase createGameUseCase;
    private final UpdateGameUseCase updateGameUseCase;
    private final RemoveGameUseCase removeGameUseCase;

    /**
     * Adds a new game to a competition.
     * @param request the AddGameRequest containing the game data
     */
    @PostMapping("/add")
    public void addGame(
            @RequestBody AddGameRequest request
    ) {
        createGameUseCase.createGame(request);
    }

    @PostMapping("/update")
    public void updateGame(
            @RequestBody UpdateGameRequest request
    ) {
        updateGameUseCase.updateGame(request);
    }

    @DeleteMapping("/remove")
    public void removeGame(
            @RequestParam String gameId
    ) {
        removeGameUseCase.removeGame(gameId);
    }
}
