package unisa.esbetstart.slipmanagment.application.port.out;

import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;

public interface PlaceBetPortOut {

    void placeBet(BetPlaced betPlaced);

}
