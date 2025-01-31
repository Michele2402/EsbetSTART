package unisa.esbetstart.eventmanagement.application.port.out;

import unisa.esbetstart.eventmanagement.domain.model.Competition;

import java.util.List;
import java.util.UUID;

public interface GetCompetitionPortOut {

    Competition getCompetitionByIdWithRules(UUID competitionId);
    Competition getCompetitionByIdWithSimpleGame(UUID competitionId);
    Competition getCompetitionByIdWithEventsList(UUID competitionId);

    List<Competition> getAllByGameId(UUID gameId);
}
