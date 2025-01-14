package unisa.esbetstart.eventmanagement.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.eventmanagement.application.mapper.ApplicationRuleMapper;
import unisa.esbetstart.eventmanagement.application.port.in.CreateGameUseCase;
import unisa.esbetstart.eventmanagement.application.port.out.CreateGamePortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetGamePortOut;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.presentation.request.AddGameRequest;
import unisa.esbetstart.eventmanagement.presentation.request.AddRuleRequest;
import unisa.esbetstart.common.utils.CheckTypeAttribute;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service class for managing the creation of games.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CreateGameManagerService implements CreateGameUseCase {

    private final ApplicationRuleMapper applicationRuleMapper;
    private final CheckTypeAttribute checkTypeAttribute;
    private final GetGamePortOut getGamePortOut;
    private final CreateGamePortOut createGamePortOut;

    /**
     * Creates a new game and adds it to the database.
     * @param request the AddGameRequest containing the game data
     */
    @Override
    public void createGame(AddGameRequest request) {
        log.info("Adding game {} to database", request.getName());

        // Check the game data
        checkAddGameRequest(request);

        // Build the Game object
        Game game = Game.builder()
                .id(UUID.randomUUID())
                .name(request.getName())
                .rules(request.getRules().stream().map(applicationRuleMapper::toRuleModel).collect(Collectors.toSet()))
                .build();

        game.getRules().forEach(rule -> rule.setGame(game));

        // Check if a game with the same name already exists
        if (getGamePortOut.getGameByName(request.getName()) != null) {
            log.error("Game with name {} already exists", request.getName());
            throw new ObjectIsNullException("Game with name " + request.getName() + " already exists");
        }

        // Add the game to the database
        createGamePortOut.addGame(game);

        log.info("Game {} added to database", request.getName());
    }

    /**
     * Checks if the AddGameRequest is valid.
     * @param request the AddGameRequest to check
     */
    private void checkAddGameRequest(AddGameRequest request) {
        if (request == null) {
            log.error("AddGameRequest is null");
            throw new ObjectIsNullException("AddGameRequest is null");
        }

        // Check the game name
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getName(), "Name");

        // Check each rule in the request
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