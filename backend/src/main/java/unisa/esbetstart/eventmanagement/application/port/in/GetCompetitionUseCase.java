package unisa.esbetstart.eventmanagement.application.port.in;

import unisa.esbetstart.eventmanagement.domain.model.Competition;

import java.util.List;

public interface GetCompetitionUseCase {
    List<Competition> getAllByGameId(String gameId);
}
