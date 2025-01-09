package unisa.esbetstart.eventmanagement.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.eventmanagement.application.port.in.CreateGameUseCase;
import unisa.esbetstart.eventmanagement.presentation.request.AddGameRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/games")
@CrossOrigin("*")
public class GameController {

    private final CreateGameUseCase createGameUseCase;

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

}
