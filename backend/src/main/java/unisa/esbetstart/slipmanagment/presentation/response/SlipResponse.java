package unisa.esbetstart.slipmanagment.presentation.response;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlipResponse {

    private String id;
    private double amount;
    private Set<SlipOddResponse> odds;

}
