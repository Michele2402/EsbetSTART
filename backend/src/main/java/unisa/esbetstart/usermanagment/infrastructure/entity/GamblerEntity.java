package unisa.esbetstart.usermanagment.infrastructure.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import unisa.esbetstart.slipmanagment.infrastructure.entity.BetPlacedEntity;
import unisa.esbetstart.slipmanagment.infrastructure.entity.SlipEntity;
import unisa.esbetstart.ticketmanagment.infrastructure.entity.TicketEntity;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.ActivatedOfferEntity;
import unisa.esbetstart.transactionmanagment.infrastructure.entity.TransactionEntity;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@DiscriminatorValue("GAMBLER")
public class GamblerEntity extends UserEntity {

    private Integer balance;

    private Integer bonusBalance;

    private Integer withdrawableBalance;

    @OneToOne(mappedBy = "gambler", cascade = CascadeType.ALL)
    private SlipEntity slip;

    @OneToMany(mappedBy = "gambler")
    private Set<BetPlacedEntity> bets;

    @OneToMany(mappedBy = "gambler", cascade = CascadeType.ALL)
    private Set<ActivatedOfferEntity> activatedOffers;

    @OneToMany(mappedBy = "gambler")
    private Set<TicketEntity> tickets;

    @OneToMany(mappedBy = "gambler")
    private Set<TransactionEntity> transactions;

}
