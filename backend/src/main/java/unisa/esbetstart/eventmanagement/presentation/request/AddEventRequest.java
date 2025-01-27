package unisa.esbetstart.eventmanagement.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddEventRequest {

    private String competitionId;
    private String name;
    private String date;
    private List<AddOddRequest> odds;

}