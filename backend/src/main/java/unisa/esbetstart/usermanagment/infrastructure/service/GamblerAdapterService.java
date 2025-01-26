package unisa.esbetstart.usermanagment.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unisa.esbetstart.transactionmanagment.domain.enums.TransactionTypeEnum;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;
import unisa.esbetstart.usermanagment.infrastructure.mapper.InfrastructureUserMapper;
import unisa.esbetstart.usermanagment.infrastructure.repository.GamblerJpaRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GamblerAdapterService implements GetGamblerPortOut {

    private final GamblerJpaRepository gamblerJpaRepository;

    private final InfrastructureUserMapper infrastructureUserMapper;

    @Override
    public Gambler getGamblerByEmailWithActivatedOffers(String email) {
        Optional<GamblerEntity> optionalGambler = gamblerJpaRepository.findByEmailWithActivatedOffers(email);

        return optionalGambler.map(infrastructureUserMapper::toGamblerModelWithActivatedOffers).orElse(null);
    }

    @Override
    public Gambler getGamblerByEmailWithTransactions(String email, TransactionTypeEnum typeEnum) {
        Optional<GamblerEntity> optionalGambler = gamblerJpaRepository.findByEmailWithTransactions(email, typeEnum);

        return optionalGambler.map(infrastructureUserMapper::toGamblerModelWithTransactions).orElse(null);
    }

    @Override
    public Gambler getGamblerByEmailWithBets(String email) {
        Optional<GamblerEntity> optionalGambler = gamblerJpaRepository.findByEmailWithBets(email);

        return optionalGambler.map(infrastructureUserMapper::toGabmlerModelWithBets).orElse(null);
    }

    @Override
    public Gambler getGamblerByEmailWithRunningBets(String email) {
        Optional<GamblerEntity> optionalGambler = gamblerJpaRepository.findByEmailWithRunningBets(email);

        return optionalGambler.map(infrastructureUserMapper::toGabmlerModelWithBets).orElse(null);
    }

    @Override
    public Gambler getGamblerByEmail(String email) {
        Optional<GamblerEntity> optionalGambler = gamblerJpaRepository.findByEmail(email);

        return optionalGambler.map(infrastructureUserMapper::toGamblerModel).orElse(null);
    }
}
