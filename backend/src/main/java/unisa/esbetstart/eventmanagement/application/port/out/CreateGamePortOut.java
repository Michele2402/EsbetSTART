package unisa.esbetstart.eventmanagement.application.port.out;

import unisa.esbetstart.eventmanagement.domain.model.Game;

public interface CreateGamePortOut {

    void addGame(Game gameName);

}
