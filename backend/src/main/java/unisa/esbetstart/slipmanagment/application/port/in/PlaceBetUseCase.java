package unisa.esbetstart.slipmanagment.application.port.in;

public interface PlaceBetUseCase {

    /**
     * Places a bet of a slip.
     * @param slipId the id of the slip
     */
    void placeBet(String slipId);

}
