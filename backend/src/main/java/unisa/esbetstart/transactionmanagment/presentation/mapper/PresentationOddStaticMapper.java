package unisa.esbetstart.transactionmanagment.presentation.mapper;


import org.springframework.stereotype.Component;
import unisa.esbetstart.slipmanagment.domain.model.OddStatic;
import unisa.esbetstart.transactionmanagment.presentation.response.OddStaticResponse;

@Component
public class PresentationOddStaticMapper {

    public OddStaticResponse toOddStaticResponse(OddStatic oddStatic) {

        return OddStaticResponse.builder()
                .event(oddStatic.getEvent())
                .competition(oddStatic.getCompetition())
                .game(oddStatic.getGame())
                .name(oddStatic.getName())
                .result(oddStatic.getResult().toString())
                .value(oddStatic.getValue())
                .build();
    }
}
