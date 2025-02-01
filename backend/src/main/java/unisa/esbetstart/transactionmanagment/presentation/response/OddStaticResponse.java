package unisa.esbetstart.transactionmanagment.presentation.response;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OddStaticResponse {

    private String event;
    private String competition;
    private String game;
    private String result;
    private String name;
    private double value;

}
