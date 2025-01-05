package unisa.esbetstart.usermanagment.application.port.out;

import unisa.esbetstart.usermanagment.domain.model.User;

public interface FindUserPortOut {

    User findUserByEmail(String email);

}
