package unisa.esbetstart.usermanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.usermanagment.application.port.in.GetUserUseCase;
import unisa.esbetstart.usermanagment.application.port.out.GetUserPortOut;
import unisa.esbetstart.usermanagment.domain.model.User;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetUserManagerService implements GetUserUseCase {

    private final GetUserPortOut getUserPortOut;

    @Override
    public User getUserByEmail(String email) {

        log.info("getting user with email {}", email);

        User user = getUserPortOut.getUserByEmail(email);

        if(user == null) {
            log.error("user with email {} not found", email);
            throw new ObjectNotFoundException("user with email " + email + " not found");
        }

        log.info("user with email {} found", email);

        return user;
    }
}
