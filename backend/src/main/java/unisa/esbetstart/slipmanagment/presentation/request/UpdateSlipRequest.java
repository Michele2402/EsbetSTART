package unisa.esbetstart.slipmanagment.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSlipRequest {

    private double amount;
    private Set<String> oddsIds;
    private String slipId;

}
