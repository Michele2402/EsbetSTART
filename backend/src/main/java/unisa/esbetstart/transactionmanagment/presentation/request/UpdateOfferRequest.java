package unisa.esbetstart.transactionmanagment.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unisa.esbetstart.transactionmanagment.domain.enums.OfferTypeEnum;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOfferRequest {

    private String offerId;
    private String description;
    private String name;
    private LocalDateTime expirationDate;
    private Integer goal;
    private OfferTypeEnum type;
    private Float price;
}
