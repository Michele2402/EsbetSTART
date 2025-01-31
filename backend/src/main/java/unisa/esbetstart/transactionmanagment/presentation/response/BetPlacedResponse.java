package unisa.esbetstart.transactionmanagment.presentation.response;


import lombok.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BetPlacedResponse {

    private double amount;
    private String date;
    private String result;
    private List<OddStaticResponse> oddStatics;

}
