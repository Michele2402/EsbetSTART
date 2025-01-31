package unisa.esbetstart.eventmanagement.presentation.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetitionResponse {
    private String id;
    private String name;
    private String originCountry;
}
