package unisa.esbetstart.transactionmanagment.presentation.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;
import unisa.esbetstart.transactionmanagment.presentation.response.BetPlacedResponse;
import unisa.esbetstart.transactionmanagment.presentation.response.OddStaticResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PresentationBetPlacedMapper {

    private final PresentationOddStaticMapper presentationOddStaticMapper;

    public Set<BetPlacedResponse> toBetResponseSet(Set<BetPlaced> bets) {
        return bets.stream().map(this::toBetResponse)
                .collect(Collectors.toSet());
    }

    public BetPlacedResponse toBetResponse(BetPlaced bet) {
        List<OddStaticResponse> oddStaticResponses = bet.getOddStatics().stream()
                .map(presentationOddStaticMapper::toOddStaticResponse).toList();

        return BetPlacedResponse.builder()
                .amount(bet.getAmount())
                .result(bet.getResult().toString())
                .date(bet.getDate().toString())
                .oddStatics(oddStaticResponses)
                .build();
    }
}
