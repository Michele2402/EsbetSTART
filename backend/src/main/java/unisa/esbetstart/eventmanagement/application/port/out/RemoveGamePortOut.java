package unisa.esbetstart.eventmanagement.application.port.out;

import java.util.UUID;

public interface RemoveGamePortOut {
    void removeGame(UUID gameId);
}
