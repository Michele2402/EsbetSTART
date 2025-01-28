package unisa.esbetstart.usermanagment.application.port.in;

import unisa.esbetstart.usermanagment.domain.model.User;

public interface GetUserUseCase {
    User getUserByEmail(String email);
}
