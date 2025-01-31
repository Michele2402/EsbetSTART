package unisa.esbetstart.usermanagment.domain.model;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.DomainAttributeException;
import unisa.esbetstart.slipmanagment.domain.model.Slip;
import unisa.esbetstart.slipmanagment.domain.model.BetPlaced;
import unisa.esbetstart.ticketmanagment.domain.enums.TicketStatusEnum;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;
import unisa.esbetstart.transactionmanagment.domain.enums.OfferTypeEnum;
import unisa.esbetstart.transactionmanagment.domain.enums.TransactionTypeEnum;
import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
import unisa.esbetstart.transactionmanagment.domain.model.Transaction;
import unisa.esbetstart.usermanagment.domain.enums.RolesEnum;

import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Slf4j
@SuperBuilder
public class Gambler extends User {

    private double balance;
    private double bonusBalance;
    private double withdrawableBalance;
    private Set<ActivatedOffer> activatedOffers;
    private Set<Ticket> tickets;
    private Slip slip;
    private Set<BetPlaced> bets;
    private Set<Transaction> transactions;

    public Gambler(String name, String surname, String email, String username, String password, double balance, double bonusBalance, double withdrawalBalance, Set<ActivatedOffer> activatedOffers, Set<Ticket> tickets, Set<BetPlaced> bets, Set<Transaction> transactions, Slip slip) {
        super(name, surname, email, username, password, RolesEnum.GAMBLER);
        this.balance = balance;
        this.bonusBalance = bonusBalance;
        this.withdrawableBalance = withdrawalBalance;
        this.activatedOffers = activatedOffers;
        this.tickets = tickets;
        this.slip = slip;
        this.bets = bets;
        this.transactions = transactions;
    }


    /**
     * This method deposits money into the balance
     *
     * @param amount the amount to be deposited
     * @throws DomainAttributeException if the amount is less than 10
     */
    public void deposit(double amount) {

        if (amount <= 10) {
            log.error("Deposit amount must be greater than 10");
            throw new DomainAttributeException("Deposit amount must be greater than 10");
        }
        this.balance += amount;

        //check if you made any progress in the activated offers present in the gambler, and if the goal is reached, add the bonus to the bonus balance
        for (ActivatedOffer activatedOffer : activatedOffers) {
            if (activatedOffer.getOffer().getType().equals(OfferTypeEnum.DEPOSIT)) {
                activatedOffer.setProgress(activatedOffer.getProgress() + amount);
                if (activatedOffer.getProgress() >= activatedOffer.getOffer().getGoal()) {
                    this.bonusBalance += activatedOffer.getOffer().getBonus();
                    log.info("Bonus added to bonus balance");
                }
            }
        }
        activatedOffers.removeIf(activatedOffer -> activatedOffer.getProgress() >= activatedOffer.getOffer().getGoal());
    }


    /**
     * This method withdraws money from the balance
     *
     * @param amount the amount to be withdrawn
     * @throws DomainAttributeException if the amount is greater than the balance
     */
    public void withdraw(double amount) {

        if (amount > this.withdrawableBalance) {
            log.error("Withdraw amount cannot be greater than balance");
            throw new DomainAttributeException("Withdraw amount cannot be greater than balance");
        }

        this.withdrawableBalance -= amount;

    }

    /**
     * This method accepts an offer
     *
     * @param offer the offer to be accepted
     * @return the activated offer
     */
    public ActivatedOffer acceptOffer(Offer offer) {

        //check if the offer has already been activated by the gambler
        for (ActivatedOffer activatedOffer : activatedOffers) {
            if (activatedOffer.getOffer().getId().equals(offer.getId())) {
                log.error("Offer already activated");
                throw new DomainAttributeException("Offer already activated");
            }
        }

        return ActivatedOffer.builder()
                .id(UUID.randomUUID())
                .gambler(this)
                .offer(offer)
                .progress(0)
                .build();

    }

    /**
     * This method creates a ticket
     *
     * @param category the category of the ticket
     * @return the ticket
     */
    public Ticket createTicket(String category) {

        return Ticket.builder()
                .id(UUID.randomUUID())
                .category(category)
                .status(TicketStatusEnum.PENDING)
                .openedBy(this)
                .build();

    }

    /**
     * This method updates the gambler's information
     *
     * @param name     the new name
     * @param surname  the new surname
     * @param username the new username
     */
    public void update(String name, String surname, String username) {
        super.update(name, surname, username);
    }

    /**
     * This method places a bet
     *
     * @param amount the amount to be bet
     */
    public void placeBet(Double amount) {

        if (amount > this.balance + this.bonusBalance + this.withdrawableBalance) {
            log.error("Bet amount cannot be greater than balance");
            throw new DomainAttributeException("Bet amount cannot be greater than balance");
        }

        //first subtract from the bonus balance, if ends subtract from the balance, if ends subtract from the withdrawable balance
        if (this.bonusBalance >= amount) {
            this.bonusBalance -= amount;
        } else if (this.bonusBalance + this.balance >= amount) {
            this.balance -= amount - this.bonusBalance;
            this.bonusBalance = 0;
        } else {
            this.withdrawableBalance -= amount - this.bonusBalance - this.balance;
            this.balance = 0;
            this.bonusBalance = 0;
        }

        //check if any activated offer has progress to be made and if the goal is reached, add the bonus to the bonus balance and remove the offer from the activated offers
        activatedOffers.removeIf(activatedOffer -> {
            if (activatedOffer.getOffer().getType().equals(OfferTypeEnum.BET)) {
                activatedOffer.setProgress(activatedOffer.getProgress() + amount);
                if (activatedOffer.getProgress() >= activatedOffer.getOffer().getGoal()) {
                    this.bonusBalance += activatedOffer.getOffer().getBonus();
                    log.info("Bonus added to bonus balance");
                    return true;
                }
            }
            return false;
        });

    }

}
