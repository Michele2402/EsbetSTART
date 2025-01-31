package unisa.esbetstart.usermanagment.presentation.response;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BalanceResponse {

    private double balance;
    private double withdrawableBalance;
    private double bonusBalance;

}
