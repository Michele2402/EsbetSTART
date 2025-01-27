package unisa.esbetstart.transactionmanagment.presentation.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferResponse {
    private String id;
    private String description;
    private String name;
    private String expirationDate;
    private String type;
    private double goal;
    private double price;
}
