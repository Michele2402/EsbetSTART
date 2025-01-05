package unisa.esbetstart.usermanagment.application.port.in;

import unisa.esbetstart.usermanagment.presentation.request.RegisterRequest;

public interface RegistrationUseCase {

    void register(RegisterRequest request);
}
