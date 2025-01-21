package unisa.esbetstart.eventmanagement.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.eventmanagement.application.port.out.CreateGamePortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetGamePortOut;
import unisa.esbetstart.eventmanagement.application.port.out.RemoveGamePortOut;
import unisa.esbetstart.eventmanagement.application.port.out.UpdateGamePortOut;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.infrastructure.entity.GameEntity;
import unisa.esbetstart.eventmanagement.infrastructure.mapper.InfrastructureGameMapper;
import unisa.esbetstart.eventmanagement.infrastructure.repository.GameJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameAdapterService implements GetGamePortOut, CreateGamePortOut, UpdateGamePortOut, RemoveGamePortOut {

    private final GameJpaRepository gameJpaRepository;

    private final InfrastructureGameMapper infrastructureGameMapper;

    /**
     * Gets a game by its id with the competition list from the database.
     * @param gameId the id of the game
     */
    @Override
    public Game getGameById(UUID gameId) {
        Optional<GameEntity> optionalGameEntity = gameJpaRepository.findByIdWithCompetitions(gameId);

        return optionalGameEntity
                .map(infrastructureGameMapper::toGameModelWithCompetitions)
                .orElse(null);
    }

    /**
     * Gets a game by its name with the competition list from the database.
     * @param gameName the name of the game
     */
    @Override
    public Game getGameByName(String gameName) {

        Optional<GameEntity> optionalGameEntity = gameJpaRepository.findByName(gameName);

        return optionalGameEntity
                .map(infrastructureGameMapper::toGameModelWithCompetitions)
                .orElse(null);

    }

    /**
     * Gets a game by its id without external classes from the database.
     * @param gameId the id of the game
     */
    @Override
    public Game getGameByIdWithSimpleDetails(UUID gameId) {
        Optional<GameEntity> optionalGameEntity = gameJpaRepository.findById(gameId);

        return optionalGameEntity
                .map(infrastructureGameMapper::toGameModel)
                .orElse(null);
    }

    /**
     * Gets a game by its id with the competition list and the event list from the database.
     * @param gameId the id of the game
     */
    @Override
    public Game getGameByIdWithCompetitionsAndEvents(UUID gameId) {
        Optional<GameEntity> optionalGameEntity = gameJpaRepository.findByIdWithCompetitionsAndEvents(gameId);

        return optionalGameEntity
                .map(infrastructureGameMapper::toGameModelWithCompetitionsAndEvents)
                .orElse(null);
    }

    /**
     * Gets all games from the database.
     */
    @Override
    public Set<Game> getAllGames() {

        List<GameEntity> gameEntities = gameJpaRepository.findAllWithRules();

        return infrastructureGameMapper.toGameModelWithRulesSet(gameEntities);
    }

    /**
     * Adds a new game to the database.
     * @param game the game to add
     */
    @Override
    public void addGame(Game game) {

        gameJpaRepository.save(infrastructureGameMapper.toGameEntityWithRules(game));

    }

    /**
     * Updates a game in the database.
     * @param game the game to update
     */
    @Override
    public void updateGame(Game game) {
        gameJpaRepository.save(infrastructureGameMapper.toGameEntityWithRules(game));
    }

    /**
     * Removes a game from the database.
     * @param gameId the id of the game to remove
     */
    @Override
    public void removeGame(UUID gameId) {
        gameJpaRepository.deleteById(gameId);
    }
}
