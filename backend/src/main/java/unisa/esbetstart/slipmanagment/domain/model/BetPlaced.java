package unisa.esbetstart.slipmanagment.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.DomainAttributeException;
import unisa.esbetstart.slipmanagment.domain.enums.ResultEnum;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Data
@Slf4j
public class BetPlaced {

    private UUID id;
    private double amount;
    private ResultEnum result;
    private Gambler gambler;
    private Set<OddStatic> oddStatics;
    private LocalDateTime date;

    @Builder
    public BetPlaced(double amount, Gambler gambler, ResultEnum result, Set<OddStatic> oddStatics, UUID id, LocalDateTime date) {

        this.gambler = gambler;
        this.id = id;
        this.amount = amount;
        this.result = result;
        this.oddStatics = oddStatics;
        this.date = date;

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

    /**
     * This method evaluates the BetPlaced
     * <p>
     * Sets the result of the BetPlaced to WON if all the OddStatics are WON
     * <p>
     * Sets the result of the BetPlaced to LOST if at least one OddStatic is LOST
     * <p>
     * Sets the result of the BetPlaced to PLAYING if at least one OddStatic is PLAYING and none are LOST
     * @return the result of the BetPlaced
     */
    public ResultEnum evaluateResult() {

        for(OddStatic oddStatic : oddStatics) {

            if(oddStatic.getResult() == ResultEnum.LOST) {
                result = ResultEnum.LOST;
                return ResultEnum.LOST;
            }
        }

        for (OddStatic oddStatic : oddStatics) {

            if(oddStatic.getResult() == ResultEnum.PLAYING) {
                result = ResultEnum.PLAYING;
                return ResultEnum.PLAYING;
            }
        }

        result = ResultEnum.WON;
        return ResultEnum.WON;

    }

}
