package unisa.esbetstart.eventmanagement.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisa.esbetstart.eventmanagement.application.port.out.CreateCompetitionPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetCompetitionPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.RemoveCompetitionPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.UpdateCompetitionPortOut;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.infrastructure.entity.CompetitionEntity;
import unisa.esbetstart.eventmanagement.infrastructure.mapper.InfrastructureCompetitionMapper;
import unisa.esbetstart.eventmanagement.infrastructure.repository.CompetitionJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompetitionAdapterService implements
        CreateCompetitionPortOut,
        GetCompetitionPortOut,
        UpdateCompetitionPortOut,
        RemoveCompetitionPortOut
{

    private final CompetitionJpaRepository competitionJpaRepository;

    private final InfrastructureCompetitionMapper  infrastructureCompetitionMapper;

    /**
     * Adds a new competition to a game.
     * @param competition the competition to add
     */
    @Override
    public void addCompetition(Competition competition) {
        competitionJpaRepository.save(infrastructureCompetitionMapper.toCompetitionEntity(competition));
    }

    /**
     * Gets a competition by its id from the database.
     * Only the game id is retrieved.
     * @param competitionId the id of the competition
     */
    @Override
    @Transactional
    public Competition getCompetitionByIdWithRules(UUID competitionId) {

        Optional<CompetitionEntity> optionalCompetition = competitionJpaRepository.findByIdWithGameRules(competitionId);
        return optionalCompetition.map(infrastructureCompetitionMapper::toCompetitionWithRules).orElse(null);

    }

    /**
     * Removes a competition from the database.
     * @param competitionId the id of the competition
     */
    @Override
    public void removeCompetition(UUID competitionId) {
        competitionJpaRepository.deleteById(competitionId);
    }

    /**
     * Gets a competition by its id from the database.
     * Only the game id is retrieved.
     * @param competitionId the id of the competition
     */
    @Override
    public Competition getCompetitionByIdWithSimpleGame(UUID competitionId) {
        Optional<CompetitionEntity> optionalCompetition = competitionJpaRepository.findByIdWithGame(competitionId);
        return optionalCompetition.map(infrastructureCompetitionMapper::toCompetitionModelWithSimpleGame).orElse(null);
    }

    /**
     * Gets a competition by its id from the database.
     * It is retrieved with the list of events
     * @param competitionId the id of the competition
     */
    @Override
    public Competition getCompetitionByIdWithEventsList(UUID competitionId) {
        Optional<CompetitionEntity> optionalCompetition = competitionJpaRepository.findByIdWithEventsList(competitionId);
        return optionalCompetition.map(infrastructureCompetitionMapper::toCompetitionModelWithSimpleEventsList).orElse(null);
    }


    /**
     * Gets all competitions by game id.
     * @param gameId the id of the game
     */
    @Override
    public List<Competition> getAllByGameId(UUID gameId) {
        List<CompetitionEntity> competitions = competitionJpaRepository.findAllByGameId(gameId);
        return infrastructureCompetitionMapper.toCompetitionModelWithSimpleDetailsList(competitions);
    }

    /**
     * Updates a competition in the database.
     * @param competition the competition to update
     */
    @Override
    public void updateCompetition(Competition competition) {
        competitionJpaRepository.save(infrastructureCompetitionMapper.toCompetitionEntity(competition));
    }


}
