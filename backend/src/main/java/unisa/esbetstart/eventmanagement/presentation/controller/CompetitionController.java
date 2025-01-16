package unisa.esbetstart.eventmanagement.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.eventmanagement.application.port.in.AddCompetitionUseCase;
import unisa.esbetstart.eventmanagement.application.port.in.UpdateCompetitionUseCase;
import unisa.esbetstart.eventmanagement.presentation.request.AddCompetitionRequest;
import unisa.esbetstart.eventmanagement.presentation.request.UpdateCompetitionRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/competitions")
@CrossOrigin("*")
public class CompetitionController {

    private final AddCompetitionUseCase addCompetitionUseCase;
    private final UpdateCompetitionUseCase updateCompetitionUseCase;

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

    @PostMapping("/update")
    public void updateCompetition(
            @RequestBody UpdateCompetitionRequest request
    ) {
        updateCompetitionUseCase.updateCompetition(request);
    }

}
