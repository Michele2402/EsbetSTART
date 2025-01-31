package unisa.esbetstart.transactionmanagment.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import unisa.esbetstart.transactionmanagment.domain.enums.OfferTypeEnum;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddOfferRequest {

    private String description;
    private String name;
    private String expirationDate;
    private Integer goal;
    private String type;
    private Float price;
}
