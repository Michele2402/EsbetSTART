package unisa.esbetstart.slipmanagment.application.port.out;

import unisa.esbetstart.slipmanagment.domain.model.Slip;

import java.util.UUID;

public interface GetSlipPortOut {

    Slip getSlipById(UUID slipId);
    Slip getSlipCompleteById(UUID slipId);
    Slip getSlipWithOddsById(UUID slipId);

}
