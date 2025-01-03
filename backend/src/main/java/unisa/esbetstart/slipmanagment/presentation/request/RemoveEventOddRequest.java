package unisa.esbetstart.slipmanagment.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveEventOddRequest {

    private String slipId;
    private String oddId;
}
