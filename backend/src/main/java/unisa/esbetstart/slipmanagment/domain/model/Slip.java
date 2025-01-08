package unisa.esbetstart.slipmanagment.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.DomainAttributeException;
import unisa.esbetstart.eventmanagement.domain.model.Odd;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class Slip {

    private UUID id;
    private double amount;
    private Set<Odd> odds;

    public Slip(UUID id, double amount, Set<Odd> odds) {

        this.id = id;
        this.amount = amount;
        this.odds = odds;

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

    //TODO: implement the placeBet method
    public void placeBet() {
    }

}
