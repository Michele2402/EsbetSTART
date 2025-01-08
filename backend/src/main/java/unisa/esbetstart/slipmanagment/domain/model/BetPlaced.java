package unisa.esbetstart.slipmanagment.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.DomainAttributeException;
import unisa.esbetstart.slipmanagment.domain.enums.ResultEnum;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class BetPlaced {

    private UUID id;
    private double amount;
    private ResultEnum result;
    private Set<OddStatic> oddStatics;

    @Builder
    public BetPlaced(double amount, ResultEnum result, Set<OddStatic> oddStatics, UUID id) {

        this.id = id;
        this.amount = amount;
        this.result = result;
        this.oddStatics = oddStatics;

    }

    /**
     * This method adds an OddStatic to the BetPlaced
     * @param oddStatic the OddStatic to be added
     */
    public void addOddStatic(OddStatic oddStatic) {

        //null check
        if(oddStatic == null) {
            log.error("OddStatic cannot be null");
            throw new DomainAttributeException("OddStatic cannot be null");
        }

        this.oddStatics.add(oddStatic);
    }

    //TODO: implement the evaluateResult method
    public void evaluateResult() { }

}
