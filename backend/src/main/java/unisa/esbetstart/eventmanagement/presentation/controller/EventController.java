package unisa.esbetstart.eventmanagement.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.eventmanagement.application.port.in.CreateEventUseCase;
import unisa.esbetstart.eventmanagement.application.port.in.UpdateEventUseCase;
import unisa.esbetstart.eventmanagement.presentation.request.AddEventRequest;
import unisa.esbetstart.eventmanagement.presentation.request.UpdateEventRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/events")
@CrossOrigin("*")
public class EventController {

    private final CreateEventUseCase createEventUseCase;
    private final UpdateEventUseCase updateEventUseCase;

    @PostMapping("/add")
    public void addEvent(@RequestBody AddEventRequest request) {

        createEventUseCase.createEvent(request);

    }

    @PostMapping("/update")
    public void updateEvent(@RequestBody UpdateEventRequest request) {

        updateEventUseCase.updateEvent(request);

    }

}
