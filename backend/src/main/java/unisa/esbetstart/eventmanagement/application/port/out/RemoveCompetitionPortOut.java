package unisa.esbetstart.eventmanagement.application.port.out;

import java.util.UUID;

public interface RemoveCompetitionPortOut {
    void removeCompetition(UUID competitionId);
}
