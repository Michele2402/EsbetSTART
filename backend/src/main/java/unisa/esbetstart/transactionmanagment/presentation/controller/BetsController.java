package unisa.esbetstart.transactionmanagment.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;
import unisa.esbetstart.transactionmanagment.application.port.in.ShowBetsUseCase;
import unisa.esbetstart.transactionmanagment.presentation.mapper.PresentationBetPlacedMapper;
import unisa.esbetstart.transactionmanagment.presentation.request.ShowUserBetsRequest;
import unisa.esbetstart.transactionmanagment.presentation.response.BetPlacedResponse;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/bets")
@CrossOrigin("*")
public class BetsController {

    private final ShowBetsUseCase showBetsUseCase;
    private final PresentationBetPlacedMapper presentationBetPlacedMapper;

    @GetMapping("/show")
    public ResponseEntity<Set<BetPlacedResponse>> showBets(
            @RequestBody ShowUserBetsRequest request
    ) {
        Set<BetPlaced> bets = showBetsUseCase.showBets(request);

        return ResponseEntity.ok(presentationBetPlacedMapper.toBetResponseSet(bets));
    }

}
