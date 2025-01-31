package unisa.esbetstart.eventmanagement.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.eventmanagement.application.port.in.CreateGameUseCase;
import unisa.esbetstart.eventmanagement.application.port.in.GetGameUseCase;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.presentation.mapper.PresentationGameMapper;
import unisa.esbetstart.eventmanagement.application.port.in.RemoveGameUseCase;
import unisa.esbetstart.eventmanagement.application.port.in.UpdateGameUseCase;
import unisa.esbetstart.eventmanagement.presentation.request.AddGameRequest;
import unisa.esbetstart.eventmanagement.presentation.response.GameWithRulesResponse;

import java.util.Set;
import unisa.esbetstart.eventmanagement.presentation.request.UpdateGameRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/games")
@CrossOrigin("*")
public class GameController {

    private final CreateGameUseCase createGameUseCase;
    private final GetGameUseCase getGameUseCase;

    private final PresentationGameMapper presentationGameMapper;
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


    @GetMapping("/filter")
    public ResponseEntity<Page<GameWithRulesResponse>> getFiltered(
            @RequestParam(defaultValue = "", required = false, name = "name") String name,
            @RequestParam(defaultValue = "100", required = false) int size,
            @RequestParam(defaultValue = "0", required = false) int page
    ) {

        this.getGameUseCase.getFiltered(name, size, page);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Gets all games from the database.
     */
    @GetMapping("/get-all")
    public ResponseEntity<Set<GameWithRulesResponse>> getAllGames() {

        Set<Game> games = getGameUseCase.getAllGames();

        return ResponseEntity.ok(presentationGameMapper.toGameWithRulesResponseSet(games));
    }

    /**
     * Updates the game name and origin country.
     * @param request the UpdateGameRequest containing the game data
     */
    @PostMapping("/update")
    public void updateGame(
            @RequestBody UpdateGameRequest request
    ) {
        updateGameUseCase.updateGame(request);
    }

    /**
     * Removes a game.
     * @param gameId the id of the game to remove
     */
    @DeleteMapping("/remove/{gameId}")
    public void removeGame(
            @PathVariable String gameId
    ) {
        removeGameUseCase.removeGame(gameId);
    }
}
