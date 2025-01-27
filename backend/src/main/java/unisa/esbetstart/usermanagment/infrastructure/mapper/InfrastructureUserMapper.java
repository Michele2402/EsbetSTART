package unisa.esbetstart.usermanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.stereotype.Component;
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
                .password("Password123!")
                .build();
    }

    /**
     * Maps a User to a new GamblerEntity with initialized values
     * @param user
     * @return the new GamblerEntity
     */
    public GamblerEntity toGamblerEntity(User user) {
        return GamblerEntity.builder()
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .password(user.getPassword())
                .balance(0.0)
                .bonusBalance(0.0)
                .withdrawableBalance(0.0)
                .slip(SlipEntity.builder()
                        .id(UUID.randomUUID())
                        .gambler(GamblerEntity.builder().email(user.getEmail()).build())
                        .build())
                .build();
    }

    public GamblerEntity toGamblerEntityWithActivatedOffers(Gambler gambler) {

        return GamblerEntity.builder()
                .email(gambler.getEmail())
                .name(gambler.getName())
                .surname(gambler.getSurname())
                .username(gambler.getUsername())
                .password(gambler.getPassword())
                .balance(gambler.getBalance())
                .bonusBalance(gambler.getBonusBalance())
                .withdrawableBalance(gambler.getWithdrawableBalance())
                .activatedOffers(gambler.getActivatedOffers()
                        .stream().map(infrastructureOfferMapper::toActivatedOfferEntityWithDetails).collect(Collectors.toSet()))
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
                        .stream().map(infrastructureOfferMapper::toActivatedOfferModelWithDetails).collect(Collectors.toSet()))
                .build();
    }

    public Gambler toGamblerModelWithOffers(GamblerEntity gamblerEntity) {

        Gambler toReturn = this.toGamblerModelWithActivatedOffers(gamblerEntity);
        toReturn.setPassword(gamblerEntity.getPassword());
        toReturn.setBalance(gamblerEntity.getBalance());
        toReturn.setBonusBalance(gamblerEntity.getBonusBalance());
        toReturn.setWithdrawableBalance(gamblerEntity.getWithdrawableBalance());
        toReturn.getActivatedOffers().forEach(activatedOffer ->
                activatedOffer
                        .setOffer(infrastructureOfferMapper.toOfferModel(gamblerEntity
                                .getActivatedOffers()
                                .stream()
                                .filter(activatedOfferEntity -> activatedOfferEntity.getId()
                                        .equals(activatedOffer.getId()))
                                .findFirst()
                                .get().getOffer())));
        return toReturn;
    }
}
