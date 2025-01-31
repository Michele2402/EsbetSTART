package unisa.esbetstart.transactionmanagment.application.port.in;

import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;
import unisa.esbetstart.transactionmanagment.presentation.request.ShowUserBetsRequest;

import java.util.Set;

public interface ShowBetsUseCase {

    /**
     * Shows the bets of a user
     * @param request the request
     * @return the set of bets
     */
    Set<BetPlaced> showBets(ShowUserBetsRequest request);

}
