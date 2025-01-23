package unisa.esbetstart.slipmanagment.application.port.in;

import unisa.esbetstart.slipmanagment.domain.model.Slip;

import java.util.UUID;

public interface GetSlipUseCase {

    Slip getSlip(String slipId);

}
