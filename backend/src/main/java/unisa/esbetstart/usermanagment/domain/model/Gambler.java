package unisa.esbetstart.usermanagment.domain.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Data
@Slf4j
public class Gambler extends User {

    private double balance;
    private double bonusBalance;
    private double withdrawalBalance;

    @Builder
    public Gambler(String name, String surname, String email, String username, String password, double balance, double bonusBalance, double withdrawableBalance) {
        super(name, surname, email, username, password);
        this.balance = balance;
        this.bonusBalance = bonusBalance;
        this.withdrawalBalance = withdrawableBalance;
    }



    public boolean deposit(double amount) {

        if(amount <= 10) {
            log.error("Deposit amount must be greater than 10");
            throw new IllegalArgumentException("Deposit amount must be greater than 0");
        }
        this.balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {

        if(amount > this.withdrawalBalance) {
            log.error("Withdraw amount cannot be greater than balance");
            throw new IllegalArgumentException("Withdraw amount cannot be greater than balance");
        }

        this.balance -= amount;
        return true;
    }

    //TODO: implement the acceptOffer method
    public void acceptOffer() { }

    //TODO: implement the createTicket method
    public void createTicket() { }


}
