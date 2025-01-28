package unisa.esbetstart.usermanagment.application.port.in;

import unisa.esbetstart.usermanagment.presentation.request.UpdateGamblerRequest;

public interface UpdateUserUseCase {

    void updateGambler(UpdateGamblerRequest user);
}
