package unisa.esbetstart.eventmanagement.application.port.out;

import unisa.esbetstart.eventmanagement.domain.model.Competition;

import java.util.UUID;

public interface GetCompetitionPortOut {

    Competition getCompetitionByIdWithRules(UUID competitionId);
}
