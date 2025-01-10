package unisa.esbetstart.eventmanagement.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unisa.esbetstart.eventmanagement.application.port.out.CreateCompetitionPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetCompetitionPortOut;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.infrastructure.entity.CompetitionEntity;
import unisa.esbetstart.eventmanagement.infrastructure.mapper.InfrastructureCompetitionMapper;
import unisa.esbetstart.eventmanagement.infrastructure.repository.CompetitionJpaRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompetitionAdapterService implements CreateCompetitionPortOut, GetCompetitionPortOut {

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
}
