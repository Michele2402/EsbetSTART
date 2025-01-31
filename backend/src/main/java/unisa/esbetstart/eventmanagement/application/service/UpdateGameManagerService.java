package unisa.esbetstart.eventmanagement.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.eventmanagement.application.mapper.ApplicationRuleMapper;
import unisa.esbetstart.eventmanagement.application.port.in.UpdateGameUseCase;
import unisa.esbetstart.eventmanagement.application.port.out.GetGamePortOut;
import unisa.esbetstart.eventmanagement.application.port.out.UpdateGamePortOut;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.domain.model.Rule;
import unisa.esbetstart.eventmanagement.presentation.request.AddRuleRequest;
import unisa.esbetstart.eventmanagement.presentation.request.UpdateGameRequest;
import unisa.esbetstart.common.utils.*;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateGameManagerService implements UpdateGameUseCase {

    private final CheckTypeAttribute checkTypeAttribute;
    private final GetGamePortOut getGamePortOut;
    private final ApplicationRuleMapper applicationRuleMapper;
    private final UpdateGamePortOut updateGamePortOut;
    private final ParseAttribute parseAttribute;

    @Override
    public void updateGame(UpdateGameRequest request) {

        log.info("Updating game with id: {}", request.getGameId());

        //check the update game request
        UUID gameId = parseAttribute.checkUUIDIsNullOrInvalid(request.getGameId(), "Game Id in update call");

        //check the update game request
        checkUpdateGameRequest(request);

        //get the game
        Game gameToUpdate = getGamePortOut.getGameByIdWithSimpleDetails(gameId);

        //check if the game exists
        if (gameToUpdate == null) {
            log.error("Game with id {} not found", gameId.toString());
            throw new ObjectIsNullException("Game with id " + gameId + " not found");
        }

        Set<Rule> rules = request.getRules().stream().map(applicationRuleMapper::toRuleModel).collect(Collectors.toSet());
        rules.forEach(rule -> rule.setGame(gameToUpdate));

        //update the game model
        gameToUpdate.updateGame(request.getName(), rules);;

        //update the game
        updateGamePortOut.updateGame(gameToUpdate);

        log.info("Game with id {} updated", gameId.toString());
    }

    private void checkUpdateGameRequest(UpdateGameRequest request) {

        if (request == null) {
            log.error("Update game request is null");
            throw new ObjectIsNullException("Update game request is null");
        }

        checkTypeAttribute.checkStringIsNullOrEmpty(request.getName(), "Name in update game request");
        request.getRules().forEach(this::checkAddRuleRequest);
    }

    /**
     * Checks if the AddRuleRequest is valid.
     * @param request the AddRuleRequest to check
     */
    private void checkAddRuleRequest(AddRuleRequest request) {
        if (request == null) {
            log.error("AddRuleRequest is null");
            throw new ObjectIsNullException("AddRuleRequest is null");
        }

        // Check the rule name
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getName(), "Rule name");

        // Check the rule position
        checkTypeAttribute.checkIntegerIsNullOrNegative(request.getPosition(), "Rule position");
    }


}
