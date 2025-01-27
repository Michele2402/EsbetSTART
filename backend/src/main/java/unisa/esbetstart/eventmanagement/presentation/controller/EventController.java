package unisa.esbetstart.eventmanagement.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.eventmanagement.application.port.in.*;
import unisa.esbetstart.eventmanagement.domain.model.Event;
import unisa.esbetstart.eventmanagement.presentation.mapper.PresentationEventMapper;
import unisa.esbetstart.eventmanagement.presentation.request.AddEventRequest;
import unisa.esbetstart.eventmanagement.presentation.request.EndEventRequest;
import unisa.esbetstart.eventmanagement.presentation.request.UpdateEventRequest;
import unisa.esbetstart.eventmanagement.presentation.request.UpdateOddRequest;
import unisa.esbetstart.eventmanagement.presentation.response.EventResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/events")
@CrossOrigin("*")
public class EventController {

    private final CreateEventUseCase createEventUseCase;
    private final UpdateEventUseCase updateEventUseCase;
    private final UpdateOddUseCase updateOddUseCase;
    private final RemoveEventUseCase removeEventUseCase;
    private final GetEventUseCase getEventUseCase;

    private final PresentationEventMapper presentationEventMapper;

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

    @DeleteMapping("/remove")
    public void removeEvent(@RequestParam String eventId) {

        removeEventUseCase.removeEvent(eventId);

    }

    @GetMapping("get-all-by-competition/{competitionId}")
    public ResponseEntity<List<EventResponse>> getAllByCompetitionId(
            @PathVariable String competitionId
    ) {
        List<Event> events = getEventUseCase.getAllByCompetitionId(competitionId);

        return ResponseEntity.ok(events.stream().map(presentationEventMapper::toResponse).toList());
    }

    @PostMapping("/end")
    public void endEvent(@RequestBody EndEventRequest event) {

        removeEventUseCase.endEvent(event);

    }
}
