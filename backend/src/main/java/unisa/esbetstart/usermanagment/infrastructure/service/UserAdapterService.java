package unisa.esbetstart.usermanagment.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import unisa.esbetstart.usermanagment.application.port.out.CreateUserPortOut;
import unisa.esbetstart.usermanagment.application.port.out.GetUserPortOut;
import unisa.esbetstart.usermanagment.domain.model.User;
import unisa.esbetstart.usermanagment.infrastructure.entity.UserEntity;
import unisa.esbetstart.usermanagment.infrastructure.mapper.InfrastructureUserMapper;
import unisa.esbetstart.usermanagment.infrastructure.repository.GamblerJpaRepository;
import unisa.esbetstart.usermanagment.infrastructure.repository.UserJpaRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAdapterService implements GetUserPortOut, CreateUserPortOut {

    private final UserJpaRepository userJpaRepository;
    private final GamblerJpaRepository gamblerJpaRepository;

    private final InfrastructureUserMapper infrastructureUserMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User getUserByEmail(String email) {

        Optional<UserEntity> optionalUser = userJpaRepository.findByEmail(email);

        return optionalUser.map(infrastructureUserMapper::toUserModel).orElse(null);
    }

    @Override
    public void createUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userJpaRepository.save(infrastructureUserMapper.toGamblerEntity(user));
    }

}
