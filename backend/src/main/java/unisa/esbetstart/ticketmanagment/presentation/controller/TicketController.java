package unisa.esbetstart.ticketmanagment.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.ticketmanagment.application.port.in.AcceptTicketUseCase;
import unisa.esbetstart.ticketmanagment.application.port.in.OpenTicketUseCase;
import unisa.esbetstart.ticketmanagment.application.port.in.SendMessageUseCase;
import unisa.esbetstart.ticketmanagment.presentation.request.AcceptTicketRequest;
import unisa.esbetstart.ticketmanagment.presentation.request.OpenTicketRequest;
import unisa.esbetstart.ticketmanagment.presentation.request.SendMessageRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/tickets")
@CrossOrigin("*")
public class TicketController {

    private final OpenTicketUseCase openTicketUseCase;
    private final AcceptTicketUseCase acceptTicketUseCase;
    private final SendMessageUseCase sendMessageUseCase;

    @PostMapping("/open")
    public void openTicket(
            @RequestBody OpenTicketRequest request
    ) {
        openTicketUseCase.openTicket(request);
    }

    @PostMapping("/accept")
    public void acceptTicket(
            @RequestBody AcceptTicketRequest request
    ) {
        acceptTicketUseCase.acceptTicket(request);
    }

    @PostMapping("/sendMessage")
    public void sendMessage(
            @RequestBody SendMessageRequest request
    ) {
        sendMessageUseCase.sendMessage(request);
    }
}
