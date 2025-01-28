package unisa.esbetstart.ticketmanagment.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.ticketmanagment.application.port.in.AcceptTicketUseCase;
import unisa.esbetstart.ticketmanagment.application.port.in.GetTicketsUseCase;
import unisa.esbetstart.ticketmanagment.application.port.in.OpenTicketUseCase;
import unisa.esbetstart.ticketmanagment.application.port.in.SendMessageUseCase;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;
import unisa.esbetstart.ticketmanagment.presentation.mapper.PresentationTicketMapper;
import unisa.esbetstart.ticketmanagment.presentation.request.AcceptTicketRequest;
import unisa.esbetstart.ticketmanagment.presentation.request.OpenTicketRequest;
import unisa.esbetstart.ticketmanagment.presentation.request.SendMessageRequest;
import unisa.esbetstart.ticketmanagment.presentation.response.TicketResponse;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/tickets")
@CrossOrigin("*")
public class TicketController {

    private final OpenTicketUseCase openTicketUseCase;
    private final AcceptTicketUseCase acceptTicketUseCase;
    private final SendMessageUseCase sendMessageUseCase;
    private final GetTicketsUseCase getTicketsUseCase;
    private final PresentationTicketMapper presentationTicketMapper;

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

    @PostMapping("/getByGamblerEmail")
    public ResponseEntity<Set<TicketResponse>> getTicketsByGamblerEmail(
            @RequestBody String gamblerEmail
    ) {
        List<Ticket> tickets = getTicketsUseCase.getTicketsByGamblerEmail(gamblerEmail);

        return ResponseEntity.ok(presentationTicketMapper.toTicketResponseSet(tickets));
    }

    @PostMapping("/getByOperatorEmail")
    public ResponseEntity<Set<TicketResponse>> getTicketsByOperatorId(
            @RequestBody String operatorEmail
    ) {
        List<Ticket> tickets = getTicketsUseCase.getTicketsByAssignedOperatorId(operatorEmail);

        return ResponseEntity.ok(presentationTicketMapper.toTicketResponseSet(tickets));
    }

}
