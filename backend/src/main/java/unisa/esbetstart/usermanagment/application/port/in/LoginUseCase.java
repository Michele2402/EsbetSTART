package unisa.esbetstart.usermanagment.application.port.in;

import unisa.esbetstart.usermanagment.presentation.request.LoginRequest;

public interface LoginUseCase {

    /**
     * Logs in a user
     * @param request the request
     * @return the token
     */
    String login(LoginRequest request);
}
