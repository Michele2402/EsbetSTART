package unisa.esbetstart.slipmanagment.application.port.out;

import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;

import java.util.List;

public interface GetBetPlacedPortOut {

    /**
     * Get the bet placed relative to an oddstatic
     * @return a list of all the bets placed
     */
    BetPlaced getBetPlacedByOddStaticId(String oddStaticId);

}
