package unisa.esbetstart.usermanagment.application.port.out;

import unisa.esbetstart.usermanagment.domain.model.User;

public interface CreateUserPortOut {

    void createUser(User user);
}
