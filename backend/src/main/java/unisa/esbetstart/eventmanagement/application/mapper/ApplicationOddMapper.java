package unisa.esbetstart.eventmanagement.application.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Odd;
import unisa.esbetstart.eventmanagement.presentation.request.AddOddRequest;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApplicationOddMapper {

    public Odd toOddModel (AddOddRequest request) {
        return Odd.builder()

                .id(UUID.randomUUID())
                .name(request.getName())
                .value(request.getValue())
                .build();
    }

}
