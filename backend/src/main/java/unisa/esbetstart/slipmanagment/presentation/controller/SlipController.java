package unisa.esbetstart.slipmanagment.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.slipmanagment.application.port.in.PlaceBetUseCase;
import unisa.esbetstart.slipmanagment.application.port.in.UpdateSlipUseCase;
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

    @PostMapping("/save")
    public void saveSlip(@RequestBody UpdateSlipRequest request) {

        updateSlipUseCase.updateSlip(request);

    }

    @PostMapping("/place-bet")
    public void placeBet(@RequestParam String slipId) {

        placeBetUseCase.placeBet(slipId);

    }

    @GetMapping("/get-slip/{gamblerEmail}")
    public ResponseEntity<SlipResponse> getSlip(@PathVariable String gamblerEmail) {


        return null;
    }

}
