package unisa.esbetstart.transactionmanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;
import unisa.esbetstart.transactionmanagment.application.port.in.ShowBetsUseCase;
import unisa.esbetstart.transactionmanagment.presentation.request.ShowUserBetsRequest;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShowBetsManagerService implements ShowBetsUseCase {

    private final CheckTypeAttribute checkTypeAttribute;
    private final GetGamblerPortOut getGamblerPortOut;

    @Override
    public Set<BetPlaced> showBets(ShowUserBetsRequest request) {
        log.info("Showing bets for user: {}", request.getGamblerEmail());

        // Check if the gambler email is valid
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getGamblerEmail(), "Gambler Email");

        // Check if the type is valid
        checkTypeAttribute.checkIfBooleanIsNull(request.isPending(), "Pending");

        Gambler gambler;

        if(getGamblerPortOut.getGamblerByEmail(request.getGamblerEmail()) == null) {
            log.error("Gambler with email {} not found", request.getGamblerEmail());
            throw new ObjectIsNullException("Gambler with email " + request.getGamblerEmail() + " not found");
        }

        // Get the gambler
        if(!request.isPending())
            gambler = getGamblerPortOut.getGamblerByEmailWithBets(request.getGamblerEmail());
        else {
            gambler = getGamblerPortOut.getGamblerByEmailWithRunningBets(request.getGamblerEmail());
            if (gambler == null)
                return new HashSet<BetPlaced>() {};
        }

        if(gambler == null) {
            log.error("Gambler with email {} not found", request.getGamblerEmail());
            throw new ObjectIsNullException("Gambler with email " + request.getGamblerEmail() + " not found");
        }

        // Get the bets
        Set<BetPlaced> bets = gambler.getBets();

        log.info("Bets for user {} retrieved", request.getGamblerEmail());

        return bets;

    }
}
