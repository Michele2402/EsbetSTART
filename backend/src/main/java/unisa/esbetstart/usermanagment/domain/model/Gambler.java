package unisa.esbetstart.usermanagment.domain.model;



import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.DomainAttributeException;
import unisa.esbetstart.slipmanagment.domain.model.Slip;
import unisa.esbetstart.ticketmanagment.domain.enums.TicketStatusEnum;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;
import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
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

    public Gambler(String name, String surname, String email, String username, String password, double balance, double bonusBalance, double withdrawalBalance, Set<ActivatedOffer> activatedOffers, Set<Ticket> tickets, Slip slip) {
        super(name, surname, email, username, password, RolesEnum.GAMBLER);
        this.balance = balance;
        this.bonusBalance = bonusBalance;
        this.withdrawableBalance = withdrawalBalance;
        this.activatedOffers = activatedOffers;
        this.tickets = tickets;
        this.slip = slip;
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
            throw new DomainAttributeException("Deposit amount must be greater than 0");
        }
        this.balance += amount;
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

    public Gambler update(String name, String surname, String username) {
        return Gambler.builder()
                .name(name)
                .surname(surname)
                .username(username)
                .email(getEmail())
                .password(getPassword())
                .balance(getBalance())
                .bonusBalance(getBonusBalance())
                .withdrawableBalance(getWithdrawableBalance())
                .build();
    }

}
