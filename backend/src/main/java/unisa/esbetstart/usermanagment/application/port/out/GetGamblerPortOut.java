package unisa.esbetstart.usermanagment.application.port.out;

import unisa.esbetstart.usermanagment.domain.model.Gambler;

public interface GetGamblerPortOut {
    Gambler getGamblerByEmail(String email);
}
