package unisa.esbetstart.transactionmanagment.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOfferRequest {

    private String offerId;
    private String description;
    private String name;
    private String expirationDate;
    private Integer goal;
    private String type;
    private Float price;
}
