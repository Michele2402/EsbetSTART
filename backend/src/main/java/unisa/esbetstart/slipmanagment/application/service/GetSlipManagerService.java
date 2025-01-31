package unisa.esbetstart.slipmanagment.application.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.slipmanagment.application.port.in.GetSlipUseCase;
import unisa.esbetstart.slipmanagment.application.port.out.GetSlipPortOut;
import unisa.esbetstart.slipmanagment.domain.model.Slip;
import unisa.esbetstart.slipmanagment.infrastructure.entity.SlipEntity;
import unisa.esbetstart.slipmanagment.infrastructure.repository.SlipJpaRepository;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetSlipManagerService implements GetSlipUseCase{

    private final GetGamblerPortOut getGamblerPortOut;
    private final GetSlipPortOut getSlipPortOut;

    @Override
    public Slip getSlip(String gamblerEmail) {

        //get the gambler by email
        Gambler gambler = getGamblerPortOut.getGamblerByEmailWithSlip(gamblerEmail);

        //check if the gambler is null
        if(gambler == null){
            throw new ObjectNotFoundException("Gambler with email " + gamblerEmail + " not found");
        }

        Slip slip = getSlipPortOut.getSlipWithOddsById(gambler.getSlip().getId());

        if (slip == null) {
            throw new ObjectNotFoundException("Slip with id " + gambler.getSlip().getId() + " not found");
        }

        return slip;

    }
}
