package unisa.esbetstart.usermanagment.application.port.in;

import unisa.esbetstart.usermanagment.domain.model.User;

public interface GetUserUseCase {
    /**
     * Gets a user by email
     * @param email the email
     * @return the user
     */
    User getUserByEmail(String email);
}
