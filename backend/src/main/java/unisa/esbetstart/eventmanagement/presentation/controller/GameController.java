package unisa.esbetstart.eventmanagement.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.eventmanagement.application.port.in.CreateGameUseCase;
import unisa.esbetstart.eventmanagement.application.port.in.GetGameUseCase;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.presentation.mapper.PresentationGameMapper;
import unisa.esbetstart.eventmanagement.presentation.request.AddGameRequest;
import unisa.esbetstart.eventmanagement.presentation.response.GameWithRulesResponse;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/games")
@CrossOrigin("*")
public class GameController {

    //TODO aggiungere un immagine ai giochi nel database

    private final CreateGameUseCase createGameUseCase;
    private final GetGameUseCase getGameUseCase;

    private final PresentationGameMapper presentationGameMapper;

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

    /**
     * Gets all games from the database.
     */
    @GetMapping("/get-all")
    public ResponseEntity<Set<GameWithRulesResponse>> getAllGames() {

        Set<Game> games = getGameUseCase.getAllGames();

        return ResponseEntity.ok(presentationGameMapper.toGameWithRulesResponseSet(games));
    }

}
