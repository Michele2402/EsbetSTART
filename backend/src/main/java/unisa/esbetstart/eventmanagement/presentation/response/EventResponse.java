package unisa.esbetstart.eventmanagement.presentation.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventResponse {

    private String id;
    private String name;
    private String date;
    private boolean isEnded;
    private List<OddResponse> odds;
}
