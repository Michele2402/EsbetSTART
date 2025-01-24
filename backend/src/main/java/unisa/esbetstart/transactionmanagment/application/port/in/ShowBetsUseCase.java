package unisa.esbetstart.transactionmanagment.application.port.in;

import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;
import unisa.esbetstart.transactionmanagment.presentation.request.ShowUserBetsRequest;

import java.util.Set;

public interface ShowBetsUseCase {
    Set<BetPlaced> showBets(ShowUserBetsRequest request);
}
