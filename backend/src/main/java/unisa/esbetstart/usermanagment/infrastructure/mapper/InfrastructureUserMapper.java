package unisa.esbetstart.usermanagment.infrastructure.mapper;

import org.springframework.stereotype.Component;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.domain.model.User;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;
import unisa.esbetstart.usermanagment.infrastructure.entity.UserEntity;

@Component
public class InfrastructureUserMapper {

    public User toUserModel(UserEntity userEntity) {
        return User.builder()
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .username(userEntity.getUsername())
                .password("Password123!")
                .build();
    }

    public GamblerEntity toGamblerEntity(User user) {
        return GamblerEntity.builder()
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .password(user.getPassword())
                .balance(0)
                .bonusBalance(0)
                .withdrawableBalance(0)
                .build();
    }

    public Gambler toGamblerModel(GamblerEntity gamblerEntity) {
        return Gambler.builder()
                .email(gamblerEntity.getEmail())
                .name(gamblerEntity.getName())
                .surname(gamblerEntity.getSurname())
                .username(gamblerEntity.getUsername())
                .password("Password123!")
                .balance(0)
                .bonusBalance(0)
                .withdrawalBalance(0)
                .build();
    }
}
