package unisa.esbetstart.usermanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.slipmanagment.infrastructure.mapper.InfrastructureBetMapper;
import unisa.esbetstart.transactionmanagment.infrastructure.mapper.InfrastructureOfferMapper;
import unisa.esbetstart.transactionmanagment.infrastructure.mapper.InfrastructureTransactionMapper;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.domain.model.User;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;
import unisa.esbetstart.usermanagment.infrastructure.entity.UserEntity;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InfrastructureUserMapper {

    private final InfrastructureOfferMapper infrastructureOfferMapper;
    private final InfrastructureTransactionMapper infrastructureTransactionMapper;
    private final InfrastructureBetMapper  infrastructureBetMapper;

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

    public Gambler toGamblerModelWithActivatedOffers(GamblerEntity gamblerEntity) {
        return Gambler.builder()
                .email(gamblerEntity.getEmail())
                .name(gamblerEntity.getName())
                .surname(gamblerEntity.getSurname())
                .username(gamblerEntity.getUsername())
                .password("Password123!")
                .activatedOffers(gamblerEntity.getActivatedOffers()
                        .stream().map(infrastructureOfferMapper::toActivatedOfferModel).collect(Collectors.toSet()))
                .build();
    }

    public Gambler toGamblerModelWithTransactions(GamblerEntity gamblerEntity) {
        return Gambler.builder()
                .email(gamblerEntity.getEmail())
                .name(gamblerEntity.getName())
                .surname(gamblerEntity.getSurname())
                .username(gamblerEntity.getUsername())
                .password("Password123!")
                .transactions(gamblerEntity.getTransactions()
                        .stream().map(infrastructureTransactionMapper::toTransactionModel).collect(Collectors.toSet()))
                .build();
    }

    public Gambler toGabmlerModelWithBets(GamblerEntity gamblerEntity) {
        return Gambler.builder()
                .email(gamblerEntity.getEmail())
                .name(gamblerEntity.getName())
                .surname(gamblerEntity.getSurname())
                .username(gamblerEntity.getUsername())
                .password("Password123!")
                .bets(gamblerEntity.getBets()
                        .stream().map(infrastructureBetMapper::toBetModelWithOddStatic).collect(Collectors.toSet()))
                .build();
    }

    public Gambler toGamblerModel(GamblerEntity gamblerEntity) {
        return Gambler.builder()
                .email(gamblerEntity.getEmail())
                .name(gamblerEntity.getName())
                .surname(gamblerEntity.getSurname())
                .username(gamblerEntity.getUsername())
                .build();
    }
}
