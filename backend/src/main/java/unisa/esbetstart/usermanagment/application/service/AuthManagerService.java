package unisa.esbetstart.usermanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.AuthorizationException;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.usermanagment.application.port.in.LoginUseCase;
import unisa.esbetstart.usermanagment.application.port.in.RegistrationUseCase;
import unisa.esbetstart.usermanagment.application.port.out.CreateUserPortOut;
import unisa.esbetstart.usermanagment.application.port.out.GetUserPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.domain.model.User;
import unisa.esbetstart.usermanagment.presentation.request.LoginRequest;
import unisa.esbetstart.usermanagment.presentation.request.RegisterRequest;
import unisa.esbetstart.usermanagment.security.CustomUserDetails;
import unisa.esbetstart.usermanagment.security.JwtService;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthManagerService implements RegistrationUseCase, LoginUseCase {

    private final CheckTypeAttribute checkTypeAttribute;

    private final GetUserPortOut getUserPortOut;
    private final CreateUserPortOut createUserPortOut;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public void register(RegisterRequest request) {

        log.info("User Registration Started");

        validateRegistrationRequest(request);

        Gambler user = Gambler.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(request.getPassword())
                .username(request.getUsername())
                .build();

        //TODO fare un api setROle, in cui si cambia il ruolo ad un utente e si cancella la schedina

        User savedUser = getUserPortOut.getUserByEmail(request.getEmail());

        if(savedUser != null) {
            log.error("User with email {} already exists", request.getEmail());
            throw new AuthorizationException("User with email " + request.getEmail() + " already exists");
        }

        createUserPortOut.createUser(user);

        log.info("User Registration Completed");
    }

    @Override
    public String login(LoginRequest request) {

        log.info("User Login Started");

        validateLoginRequest(request);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("GAMBLER");
            log.info("User Login Completed");
            return jwtService.generateToken(request.getEmail(), role);
        }

        log.error("User Login Failed");
        return "failure";
    }

    private void validateRegistrationRequest(RegisterRequest request) {

        if(request == null) {
            log.error("RegisterRequest is null");
            throw new ObjectIsNullException("RegisterRequest is null");
        }

        checkTypeAttribute.checkStringIsNullOrEmpty(request.getName(), "Name");
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getSurname(), "Surname");
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getEmail(), "Email");
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getPassword(), "Password");
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getUsername(), "Username");
    }

    private void validateLoginRequest(LoginRequest request) {

        if(request == null) {
            log.error("LoginRequest is null");
            throw new ObjectIsNullException("LoginRequest is null");
        }

        checkTypeAttribute.checkStringIsNullOrEmpty(request.getEmail(), "Email");
        checkTypeAttribute.checkStringIsNullOrEmpty(request.getPassword(), "Password");
    }
}
