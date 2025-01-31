package unisa.esbetstart.eventmanagement.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.eventmanagement.application.port.in.AddCompetitionUseCase;
import unisa.esbetstart.eventmanagement.application.port.in.GetCompetitionUseCase;
import unisa.esbetstart.eventmanagement.application.port.in.RemoveCompetitionUseCase;
import unisa.esbetstart.eventmanagement.application.port.in.UpdateCompetitionUseCase;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.presentation.mapper.PresentationCompetitionMapper;
import unisa.esbetstart.eventmanagement.presentation.request.AddCompetitionRequest;
import unisa.esbetstart.eventmanagement.presentation.request.UpdateCompetitionRequest;
import unisa.esbetstart.eventmanagement.presentation.response.CompetitionResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/competitions")
@CrossOrigin("*")
public class CompetitionController {

    private final AddCompetitionUseCase addCompetitionUseCase;
    private final UpdateCompetitionUseCase updateCompetitionUseCase;
    private final RemoveCompetitionUseCase removeCompetitionUseCase;
    private final GetCompetitionUseCase getCompetitionUseCase;

    private final PresentationCompetitionMapper presentationCompetitionMapper;

    /**
     * Adds a new competition to a game.
     * @param request the AddCompetitionRequest containing the competition data
     */
    @PostMapping("/add")
    public void addCompetition(
            @RequestBody AddCompetitionRequest request
    ) {
        addCompetitionUseCase.addCompetition(request);
    }

    /**
     * Updates the competition name and origin country.
     * @param request the UpdateCompetitionRequest containing the competition data
     */
    @PostMapping("/update")
    public void updateCompetition(
            @RequestBody UpdateCompetitionRequest request
    ) {
        updateCompetitionUseCase.updateCompetition(request);
    }

    /**
     * Removes a competition.
     * @param competitionId the id of the competition to remove
     */
    @DeleteMapping("/remove/{competitionId}")
    public void removeCompetition(
            @PathVariable String competitionId
    ) {
        removeCompetitionUseCase.removeCompetition(competitionId);
    }

    /**
     * Gets all competitions by game id.
     *
     * @param gameId the id of the game
     */
    @GetMapping("/get-all-by-game/{gameId}")
    public ResponseEntity<List<CompetitionResponse>> getAllByGameId(
            @PathVariable String gameId
    ) {

        List<Competition> competitions = getCompetitionUseCase.getAllByGameId(gameId);

        return ResponseEntity.ok(presentationCompetitionMapper.toCompetitionResponseList(competitions));
    }

}
