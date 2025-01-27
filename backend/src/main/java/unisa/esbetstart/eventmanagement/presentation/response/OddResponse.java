package unisa.esbetstart.eventmanagement.presentation.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OddResponse {

    private String id;
    private String name;
    private int position;
    private double value;
}
