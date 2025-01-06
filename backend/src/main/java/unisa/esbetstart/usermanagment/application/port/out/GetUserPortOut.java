package unisa.esbetstart.usermanagment.application.port.out;

import unisa.esbetstart.usermanagment.domain.model.User;

public interface GetUserPortOut {

    User getUserByEmail(String email);

}
