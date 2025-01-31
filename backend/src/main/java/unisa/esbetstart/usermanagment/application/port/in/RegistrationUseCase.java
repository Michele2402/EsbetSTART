package unisa.esbetstart.usermanagment.application.port.in;

import unisa.esbetstart.usermanagment.presentation.request.RegisterRequest;

public interface RegistrationUseCase {

    /**
     * Registers a user
     * @param request the request
     */
    void register(RegisterRequest request);
}
