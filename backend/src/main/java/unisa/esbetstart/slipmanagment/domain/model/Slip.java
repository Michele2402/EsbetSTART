package unisa.esbetstart.slipmanagment.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.DomainAttributeException;
import unisa.esbetstart.eventmanagement.domain.model.Odd;
import unisa.esbetstart.slipmanagment.domain.enums.ResultEnum;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
@Slf4j
public class Slip {

    private UUID id;
    private double amount;
    private Set<Odd> odds;
    private Gambler gambler;

    public Slip(UUID id, double amount, Set<Odd> odds, Gambler gambler) {
        this.id = id;
        this.amount = amount;
        this.odds = odds;
        this.gambler = gambler;
    }

    /**
     * This method adds an odd to the slip
     *
     * @param odd the odd to be added
     * @throws DomainAttributeException if the odd is null
     * @throws DomainAttributeException if the odd already exists in the slip
     */
    public void addOdd(Odd odd) {

        if (odd == null) {
            log.error("Odd cannot be null");
            throw new DomainAttributeException("Odd cannot be null");
        }

        //check if already exists with contains method
        if (odds.contains(odd)) {
            log.error("Odd {} already exists in the slip", odd.getId());
            throw new DomainAttributeException("Odd " + odd.getId() + " already exists in the slip");
        }

        this.odds.add(odd);
    }

    /**
     * This method removes an odd from the slip
     *
     * @param odd the odd to be removed
     * @throws DomainAttributeException if the odd is null
     * @throws DomainAttributeException if the odd does not exist in the slip
     */
    public void removeOdd(Odd odd) {

        if (odd == null) {
            log.error("Odd cannot be null");
            throw new DomainAttributeException("Odd cannot be null");
        }

        //check if exists with contains method
        if (!odds.contains(odd)) {
            log.error("Odd {} does not exist in the slip", odd.getId());
            throw new DomainAttributeException("Odd " + odd.getId() + " does not exist in the slip");
        }

        this.odds.remove(odd);
    }


    /**
     * This method places a bet
     *
     * @return the BetPlaced
     * @throws DomainAttributeException if the slip is empty
     * @throws DomainAttributeException if the slip amount is less than or equal to 0
     */
    public BetPlaced placeBet() {

        //check if the slip is empty
        if (odds.isEmpty()) {
            log.error("Slip cannot be empty");
            throw new DomainAttributeException("Slip cannot be empty");
        }

        //check if the slip is valid
        if (amount <= 0) {
            log.error("Slip amount must be greater than 0");
            throw new DomainAttributeException("Slip amount must be greater than 0");
        }

        return BetPlaced.builder()
                .id(UUID.randomUUID())
                .amount(amount)
                .result(ResultEnum.PLAYING)
                .gambler(gambler)
                .oddStatics(odds.stream().map(Odd::toOddStatic).collect(Collectors.toSet()))
                .build();

    }

}
