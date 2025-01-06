package unisa.esbetstart.usermanagment.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unisa.esbetstart.usermanagment.application.port.out.GetUserPortOut;
import unisa.esbetstart.usermanagment.domain.model.User;
import unisa.esbetstart.usermanagment.infrastructure.entity.UserEntity;
import unisa.esbetstart.usermanagment.infrastructure.mapper.InfrastructureUserMapper;
import unisa.esbetstart.usermanagment.infrastructure.repository.UserJpaRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAdapterService implements GetUserPortOut {

    private final UserJpaRepository userJpaRepository;

    private final InfrastructureUserMapper infrastructureUserMapper;

    @Override
    public User getUserByEmail(String email) {

        Optional<UserEntity> optionalUser = userJpaRepository.findByEmail(email);

        return optionalUser.map(infrastructureUserMapper::toUserModel).orElse(null);
    }

    //findByEmail

    //create

    //deleteAll
}
