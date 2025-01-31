package unisa.esbetstart.transactionmanagment.presentation.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivatedOfferResponse {
    private String id;
    private String description;
    private String name;
    private String type;
    private double progress;
    private double goal;
    private double bonus;
}
