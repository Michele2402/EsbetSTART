package unisa.esbetstart.eventmanagement.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOddRequest {

    private String oddId;
    private Float oddValue;
}
