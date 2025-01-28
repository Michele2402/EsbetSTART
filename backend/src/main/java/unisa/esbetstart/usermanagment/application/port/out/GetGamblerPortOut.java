package unisa.esbetstart.usermanagment.application.port.out;

import unisa.esbetstart.transactionmanagment.domain.enums.TransactionTypeEnum;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

public interface GetGamblerPortOut {
    Gambler getGamblerByEmail(String email);
    Gambler getGamblerByEmailWithOffers(String email);

    Gambler getGamblerByEmailWithActivatedOffers(String email);
    Gambler getGamblerByEmailWithTransactions(String email, TransactionTypeEnum typeEnum);
    Gambler getGamblerByEmailWithBets(String email);
    Gambler getGamblerByEmailWithRunningBets(String email);
}
