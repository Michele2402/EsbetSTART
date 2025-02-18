package unisa.esbetstart.slipmanagment.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.slipmanagment.application.port.in.GetSlipUseCase;
import unisa.esbetstart.slipmanagment.application.port.in.PlaceBetUseCase;
import unisa.esbetstart.slipmanagment.application.port.in.UpdateSlipUseCase;
import unisa.esbetstart.slipmanagment.domain.model.Slip;
import unisa.esbetstart.slipmanagment.presentation.mapper.PresentationSlipMapper;
import unisa.esbetstart.slipmanagment.presentation.request.UpdateSlipRequest;
import unisa.esbetstart.slipmanagment.presentation.response.SlipResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/slip")
@CrossOrigin("*")
public class SlipController {

    private final UpdateSlipUseCase updateSlipUseCase;
    private final PlaceBetUseCase placeBetUseCase;
    private final GetSlipUseCase getSlipUseCase;
    private final PresentationSlipMapper presentationSlipMapper;

    /**
     * This method saves a slip
     * @param request
     */
    @PostMapping("/save")
    public void saveSlip(@RequestBody UpdateSlipRequest request) {

        updateSlipUseCase.updateSlip(request);

    }

    /**
     * This method places a bet
     * @param slipId
     */
    @PostMapping("/place-bet")
    public void placeBet(@RequestBody String slipId) {

        placeBetUseCase.placeBet(slipId);

    }

    /**
     * This method gets a slip from a gamblerEmail
     * @param gamblerEmail
     * @return SlipResponse
     */
    @GetMapping("/get-slip/{gamblerEmail}")
    public ResponseEntity<SlipResponse> getSlip(@PathVariable String gamblerEmail) {

        Slip toReturn = getSlipUseCase.getSlip(gamblerEmail);
        return ResponseEntity.ok(presentationSlipMapper.toSlipResponse(toReturn));

    }

}
