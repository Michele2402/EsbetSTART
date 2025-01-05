package unisa.esbetstart.usermanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.usermanagment.application.port.in.RegistrationUseCase;
import unisa.esbetstart.usermanagment.application.port.out.FindUserPortOut;
import unisa.esbetstart.usermanagment.application.utils.CheckTypeAttribute;
import unisa.esbetstart.usermanagment.domain.model.User;
import unisa.esbetstart.usermanagment.presentation.request.RegisterRequest;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthManagerService implements RegistrationUseCase {

    private final CheckTypeAttribute checkTypeAttribute;

    private final FindUserPortOut findUserPortOut;

    @Override
    public void register(RegisterRequest request) {

        validateRequest(request);

        User user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(request.getPassword())
                .username(request.getUsername())
                .build();

        User savedUser = findUserPortOut.findUserByEmail(request.getEmail());

        if(savedUser != null) {
            log.error("User already exists");
            throw new IllegalArgumentException("User already exists");
        }

        //persistere

    }

    private void validateRequest(RegisterRequest request) {

    }
}
