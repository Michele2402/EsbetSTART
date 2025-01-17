package unisa.esbetstart.eventmanagement.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.eventmanagement.application.port.in.CreateEventUseCase;
import unisa.esbetstart.eventmanagement.application.port.in.UpdateEventUseCase;
import unisa.esbetstart.eventmanagement.application.port.in.UpdateOddUseCase;
import unisa.esbetstart.eventmanagement.presentation.request.AddEventRequest;
import unisa.esbetstart.eventmanagement.presentation.request.UpdateEventRequest;
import unisa.esbetstart.eventmanagement.presentation.request.UpdateOddRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/events")
@CrossOrigin("*")
public class EventController {

    private final CreateEventUseCase createEventUseCase;
    private final UpdateEventUseCase updateEventUseCase;
    private final UpdateOddUseCase updateOddUseCase;

    @PostMapping("/add")
    public void addEvent(@RequestBody AddEventRequest request) {

        createEventUseCase.createEvent(request);

    }

    @PostMapping("/update")
    public void updateEvent(@RequestBody UpdateEventRequest request) {

        updateEventUseCase.updateEvent(request);

    }

    @PostMapping("/odds/update")
    public void updateOdd(@RequestBody UpdateOddRequest request) {

        updateOddUseCase.updateOdd(request);

    }

}
