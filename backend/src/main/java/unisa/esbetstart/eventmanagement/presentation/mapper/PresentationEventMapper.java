package unisa.esbetstart.eventmanagement.presentation.mapper;

import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Event;
import unisa.esbetstart.eventmanagement.domain.model.Odd;
import unisa.esbetstart.eventmanagement.presentation.response.EventResponse;
import unisa.esbetstart.eventmanagement.presentation.response.OddResponse;

import java.util.Comparator;
import java.util.List;

@Component
public class PresentationEventMapper {

    public EventResponse toResponse(Event event) {
        List<OddResponse> odds = event.getOdds().stream().map(this::toOddResponse)
                .sorted(Comparator.comparingInt(OddResponse::getPosition))
                .toList();

        return EventResponse.builder()
                .id(event.getId().toString())
                .name(event.getName())
                .date(event.getDate().toString())
                .isEnded(event.isEnded())
                .odds(odds)
                .build();
    }

    public OddResponse toOddResponse(Odd odd) {
        return OddResponse.builder()
                .id(odd.getId().toString())
                .value(odd.getValue())
                .name(odd.getName())
                .position(odd.getPosition())
                .build();
    }

}
