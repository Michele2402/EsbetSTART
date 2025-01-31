package unisa.esbetstart.slipmanagment.presentation.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlipOddResponse {
    private String eventName;
    private String oddId;
    private String oddName;
    private double oddValue;
}

