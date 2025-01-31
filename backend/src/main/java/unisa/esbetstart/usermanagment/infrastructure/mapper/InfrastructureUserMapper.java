package unisa.esbetstart.usermanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.slipmanagment.domain.model.Slip;
import unisa.esbetstart.slipmanagment.infrastructure.entity.SlipEntity;
import unisa.esbetstart.slipmanagment.infrastructure.mapper.InfrastructureBetMapper;
import unisa.esbetstart.transactionmanagment.infrastructure.mapper.InfrastructureOfferMapper;
import unisa.esbetstart.transactionmanagment.infrastructure.mapper.InfrastructureTransactionMapper;
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
    private final InfrastructureTransactionMapper infrastructureTransactionMapper;
    private final InfrastructureBetMapper  infrastructureBetMapper;

    /**
     * Maps a UserEntity to a User model
     * @param userEntity
     * @return the new User
     */
    public User toUserModel(UserEntity userEntity) {
        return User.builder()
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .build();
    }

    /**
     * Maps a User to a new GamblerEntity with initialized values
     * @param user
     * @return the new GamblerEntity
     */
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

    /**
     * Maps a Gambler to a new GamblerEntity with initialized values
     * @param gambler
     * @return the new GamblerEntity
     */
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

    /**
     * Maps a GamblerEntity to a Gambler model with activated offers
     * @param gamblerEntity
     * @return the new GamblerEntity
     */
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
                        .stream().map(infrastructureOfferMapper::toActivatedOfferModelWithDetails).collect(Collectors.toSet()))
                .build();
    }

    /**
     * Maps a Gambler to a GamblerEntity
     * @param gambler
     * @return the new GamblerEntity
     */
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

    /**
     * Maps a GamblerEntity to a Gambler model with offers
     * @param gamblerEntity
     * @return the new Gambler
     */
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

    /**
     * Maps a GamblerEntity to a Gambler model with its transactions
     * @param gamblerEntity
     * @return the new Gambler
     */
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

    /**
     * Maps a GamblerEntity to a Gambler model with its bets
     * @param gamblerEntity
     * @return the new Gambler
     */
    public Gambler toGamblerModelWithBets(GamblerEntity gamblerEntity) {
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

    /**
     * Maps a GamblerEntity to a Gambler model
     * @param gamblerEntity
     * @return the new Gambler
     */
    public Gambler toGamblerModel(GamblerEntity gamblerEntity) {
        return Gambler.builder()
                .email(gamblerEntity.getEmail())
                .name(gamblerEntity.getName())
                .surname(gamblerEntity.getSurname())
                .username(gamblerEntity.getUsername())
                .build();
    }

    /**
     * Maps a GamblerEntity to a Gambler model with the slip id
     * @param gamblerEntity
     * @return the new Gambler
     */
    public Gambler toGamblerModelWithSlip(GamblerEntity gamblerEntity) {
        return Gambler.builder()
                .email(gamblerEntity.getEmail())
                .name(gamblerEntity.getName())
                .surname(gamblerEntity.getSurname())
                .username(gamblerEntity.getUsername())
                .password(gamblerEntity.getPassword())
                .slip(Slip.builder()
                        .id(gamblerEntity.getSlip().getId())
                        .build())
                .build();
    }
}
