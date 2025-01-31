package unisa.esbetstart.slipmanagment.application.port.in;

import unisa.esbetstart.slipmanagment.presentation.request.UpdateSlipRequest;

public interface UpdateSlipUseCase {

    /**
     * Updates a slip.
     * @param request the request to update the slip
     */
    void updateSlip(UpdateSlipRequest request);

}
