package unisa.esbetstart.slipmanagment.presentation.response;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlipResponse {

    private int amount;
    private Set<SlipOddResponse> odds;
}
