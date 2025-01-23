package unisa.esbetstart.slipmanagment.application.port.out;

import unisa.esbetstart.slipmanagment.domain.model.Slip;

import java.util.UUID;

public interface GetSlipByIdPortOut {

    Slip getSlipById(UUID slipId);

}
