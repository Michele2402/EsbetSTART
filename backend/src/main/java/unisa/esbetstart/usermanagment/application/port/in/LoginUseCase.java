package unisa.esbetstart.usermanagment.application.port.in;

import unisa.esbetstart.usermanagment.presentation.request.LoginRequest;

public interface LoginUseCase {

    String login(LoginRequest request);
}
