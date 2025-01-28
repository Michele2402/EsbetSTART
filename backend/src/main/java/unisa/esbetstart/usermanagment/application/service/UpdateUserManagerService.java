package unisa.esbetstart.usermanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.usermanagment.application.port.in.UpdateUserUseCase;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.application.port.out.UpdateUserPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.presentation.request.UpdateGamblerRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateUserManagerService implements UpdateUserUseCase {

    private final GetGamblerPortOut getGamblerPortOut;
    private final UpdateUserPortOut updateUserPortOut;

    @Override
    public void updateGambler(UpdateGamblerRequest request) {
        log.info("updating user {}", request.getEmail());

        Gambler gambler = getGamblerPortOut.getGamblerByEmail(request.getEmail());

        if(gambler == null) {
            log.error("gambler with email {} not found", request.getEmail());
            throw new ObjectNotFoundException("gambler with email " + request.getEmail() + " not found");
        }

        Gambler updatedGambler = gambler.update(request.getName(), request.getSurname(), request.getUsername());

        updateUserPortOut.updateGambler(updatedGambler);

        log.info("user {} updated", request.getEmail());
    }
}
