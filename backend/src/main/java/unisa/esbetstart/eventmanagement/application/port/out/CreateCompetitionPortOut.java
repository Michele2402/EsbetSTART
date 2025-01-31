package unisa.esbetstart.eventmanagement.application.port.out;

import unisa.esbetstart.eventmanagement.domain.model.Competition;

public interface CreateCompetitionPortOut {

    void addCompetition(Competition competition);
}
