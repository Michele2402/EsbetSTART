package unisa.esbetstart.eventmanagement.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.eventmanagement.application.port.out.CreateGamePortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetGamePortOut;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.infrastructure.entity.GameEntity;
import unisa.esbetstart.eventmanagement.infrastructure.mapper.InfrastructureGameMapper;
import unisa.esbetstart.eventmanagement.infrastructure.repository.GameJpaRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameAdapterService implements GetGamePortOut, CreateGamePortOut {

    private final GameJpaRepository gameJpaRepository;

    private final InfrastructureGameMapper infrastructureGameMapper;

    /**
     * Gets a game by its id from the database.
     * @param gameId the id of the game
     */
    @Override
    public Game getGameById(UUID gameId) {
        Optional<GameEntity> optionalGameEntity = gameJpaRepository.findById(gameId);

        return optionalGameEntity
                .map(infrastructureGameMapper::toGameModelWithCompetitions)
                .orElse(null);
    }

    /**
     * Gets a game by its name from the database.
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
     * Adds a new game to the database.
     * @param game the game to add
     */
    @Override
    public void addGame(Game game) {

        gameJpaRepository.save(infrastructureGameMapper.toGameEntityWithRules(game));

    }
}
