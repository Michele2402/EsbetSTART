package unisa.esbetstart.eventmanagement.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unisa.esbetstart.eventmanagement.application.port.out.CreateCompetitionPortOut;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.infrastructure.mapper.InfrastructureCompetitionMapper;
import unisa.esbetstart.eventmanagement.infrastructure.repository.CompetitionJpaRepository;

@Service
@RequiredArgsConstructor
public class CompetitionAdapterService implements CreateCompetitionPortOut {

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
}
