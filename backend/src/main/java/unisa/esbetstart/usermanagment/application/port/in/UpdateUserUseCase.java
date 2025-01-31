package unisa.esbetstart.usermanagment.application.port.in;

import unisa.esbetstart.usermanagment.presentation.request.UpdateGamblerRequest;

public interface UpdateUserUseCase {

    /**
     * Updates a gambler
     * @param user the user
     */
    void updateGambler(UpdateGamblerRequest user);
}
