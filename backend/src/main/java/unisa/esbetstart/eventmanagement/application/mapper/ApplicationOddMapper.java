package unisa.esbetstart.eventmanagement.application.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Odd;
import unisa.esbetstart.eventmanagement.presentation.request.AddOddRequest;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApplicationOddMapper {

    /**
     * Maps an AddOddRequest to an Odd model.
     * @param request the AddOddRequest to map
     * @return the Odd model
     */
    public Odd toOddModel (AddOddRequest request) {
        return Odd.builder()

                .id(UUID.randomUUID())
                .name(request.getName())
                .value(request.getValue())
                .position(request.getPosition())
                .build();
    }

}
