package unisa.esbetstart.usermanagment.application.port.out;

import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.domain.model.User;

public interface UpdateUserPortOut {

    void updateWithdrawableBalance(String userId, double amount);
    void updateGambler(Gambler user);
}
