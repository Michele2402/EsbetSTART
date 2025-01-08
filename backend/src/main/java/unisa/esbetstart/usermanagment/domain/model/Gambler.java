package unisa.esbetstart.usermanagment.domain.model;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.DomainAttributeException;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;
import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Slf4j
public class Gambler extends User {

    private double balance;
    private double bonusBalance;
    private double withdrawalBalance;
    private Set<ActivatedOffer> activatedOffers;
    private Set<Ticket> tickets;

    //TODO: michele metti sto cazzo di builder risolvi il problema del costruttore e poi fai il merge con il tuo branch

    //@Builder
    public Gambler(String name, String surname, String email, String username, String password, double balance, double bonusBalance, double withdrawalBalance, Set<ActivatedOffer> activatedOffers, Set<Ticket> tickets) {
        super(name, surname, email, username, password);
        this.balance = balance;
        this.bonusBalance = bonusBalance;
        this.withdrawalBalance = withdrawalBalance;
        this.activatedOffers = activatedOffers;
        this.tickets = tickets;
    }



    public void deposit(double amount) {

        if(amount <= 10) {
            log.error("Deposit amount must be greater than 10");
            throw new DomainAttributeException("Deposit amount must be greater than 0");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) {

        if(amount > this.withdrawalBalance) {
            log.error("Withdraw amount cannot be greater than balance");
            throw new DomainAttributeException("Withdraw amount cannot be greater than balance");
        }

    }

    //TODO: implement the acceptOffer method
    public void acceptOffer() { }

    //TODO: implement the createTicket method
    public void createTicket() { }


}
