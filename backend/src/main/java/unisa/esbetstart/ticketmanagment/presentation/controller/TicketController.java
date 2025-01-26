package unisa.esbetstart.ticketmanagment.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.ticketmanagment.application.port.in.OpenTicketUseCase;
import unisa.esbetstart.ticketmanagment.presentation.request.OpenTicketRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/tickets")
@CrossOrigin("*")
public class TicketController {

    private final OpenTicketUseCase openTicketUseCase;

    @PostMapping("/open")
    public void openTicket(
            @RequestBody OpenTicketRequest request
    ) {
        openTicketUseCase.openTicket(request);
    }
}
