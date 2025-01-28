package unisa.esbetstart.usermanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.slipmanagment.domain.model.Slip;
import unisa.esbetstart.slipmanagment.infrastructure.entity.SlipEntity;
import unisa.esbetstart.slipmanagment.infrastructure.entity.SlipEntity;
import unisa.esbetstart.transactionmanagment.infrastructure.mapper.InfrastructureOfferMapper;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.domain.model.User;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;
import unisa.esbetstart.usermanagment.infrastructure.entity.UserEntity;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InfrastructureUserMapper {

    private final InfrastructureOfferMapper infrastructureOfferMapper;

    public User toUserModel(UserEntity userEntity) {
        return User.builder()
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .build();
    }

    public GamblerEntity toGamblerEntity(User user) {

        GamblerEntity gambler = GamblerEntity.builder()
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();

        SlipEntity slip = SlipEntity.builder()
                .id(UUID.randomUUID())
                .amount(0)
                .gambler(gambler)
                .build();

        gambler.setSlip(slip);

        return gambler;
    }

    public Gambler toGamblerModelWithActivatedOffers(GamblerEntity gamblerEntity) {
        return Gambler.builder()
                .email(gamblerEntity.getEmail())
                .name(gamblerEntity.getName())
                .surname(gamblerEntity.getSurname())
                .username(gamblerEntity.getUsername())
                .password(gamblerEntity.getPassword())
                .withdrawableBalance(gamblerEntity.getWithdrawableBalance())
                .balance(gamblerEntity.getBalance())
                .bonusBalance(gamblerEntity.getBonusBalance())
                .activatedOffers(gamblerEntity.getActivatedOffers()
                        .stream().map(infrastructureOfferMapper::toActivatedOfferModel).collect(Collectors.toSet()))
                .build();
    }

    public GamblerEntity toSimpleGamblerEntity(Gambler gambler) {
        return GamblerEntity.builder()
                .email(gambler.getEmail())
                .name(gambler.getName())
                .surname(gambler.getSurname())
                .username(gambler.getUsername())
                .password(gambler.getPassword())
                .withdrawableBalance(gambler.getWithdrawableBalance())
                .balance(gambler.getBalance())
                .bonusBalance(gambler.getBonusBalance())
                .build();
    }
}
