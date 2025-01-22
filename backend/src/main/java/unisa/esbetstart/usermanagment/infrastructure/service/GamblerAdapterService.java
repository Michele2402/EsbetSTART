package unisa.esbetstart.usermanagment.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;
import unisa.esbetstart.usermanagment.infrastructure.entity.UserEntity;
import unisa.esbetstart.usermanagment.infrastructure.mapper.InfrastructureUserMapper;
import unisa.esbetstart.usermanagment.infrastructure.repository.GamblerJpaRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GamblerAdapterService implements GetGamblerPortOut {

    private final GamblerJpaRepository gamblerJpaRepository;

    private final InfrastructureUserMapper infrastructureUserMapper;

    @Override
    public Gambler getGamblerByEmail(String email) {
        Optional<GamblerEntity> optionalGambler = gamblerJpaRepository.findByEmail(email);

        return optionalGambler.map(infrastructureUserMapper::toGamblerModel).orElse(null);
    }
}
