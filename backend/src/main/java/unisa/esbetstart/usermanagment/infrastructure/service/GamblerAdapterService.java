package unisa.esbetstart.usermanagment.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.application.port.out.UpdateUserPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.domain.model.User;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;
import unisa.esbetstart.usermanagment.infrastructure.entity.UserEntity;
import unisa.esbetstart.usermanagment.infrastructure.mapper.InfrastructureUserMapper;
import unisa.esbetstart.usermanagment.infrastructure.repository.GamblerJpaRepository;
import unisa.esbetstart.usermanagment.infrastructure.repository.UserJpaRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GamblerAdapterService implements GetGamblerPortOut, UpdateUserPortOut {

    private final GamblerJpaRepository gamblerJpaRepository;

    private final InfrastructureUserMapper infrastructureUserMapper;
    private final UserJpaRepository userJpaRepository;

    @Override
    public Gambler getGamblerByEmail(String email) {
        Optional<GamblerEntity> optionalGambler = gamblerJpaRepository.findByEmail(email);
        return optionalGambler.map(infrastructureUserMapper::toGamblerModelWithActivatedOffers).orElse(null);
    }

    //TODO insultare francesco per questo metodo
    @Override
    public void updateWithdrawableBalance(String userId, double amount) {
        Optional<GamblerEntity> optionalGambler = gamblerJpaRepository.findByEmail(userId);

        if (optionalGambler.isPresent()) {
            GamblerEntity gamblerEntity = optionalGambler.get();
            gamblerEntity.setWithdrawableBalance(gamblerEntity.getWithdrawableBalance() + amount);
            gamblerJpaRepository.save(gamblerEntity);
        }
    }

    @Override
    public void updateGambler(Gambler gambler) {
        gamblerJpaRepository.save(infrastructureUserMapper.toSimpleGamblerEntity(gambler));
    }

}
