package unisa.esbetstart.usermanagment.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.usermanagment.application.port.in.CreateTransactionUseCase;
import unisa.esbetstart.usermanagment.application.port.in.GetUserUseCase;
import unisa.esbetstart.usermanagment.application.port.in.LoginUseCase;
import unisa.esbetstart.usermanagment.application.port.in.RegistrationUseCase;
import unisa.esbetstart.usermanagment.application.port.in.UpdateUserUseCase;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.domain.model.User;
import unisa.esbetstart.usermanagment.presentation.mapper.PresentationAuthMapper;
import unisa.esbetstart.usermanagment.presentation.request.CreateTransactionRequest;
import unisa.esbetstart.usermanagment.presentation.request.LoginRequest;
import unisa.esbetstart.usermanagment.presentation.request.RegisterRequest;
import unisa.esbetstart.usermanagment.presentation.request.UpdateGamblerRequest;
import unisa.esbetstart.usermanagment.presentation.response.BalanceResponse;
import unisa.esbetstart.usermanagment.presentation.response.LoginResponse;
import unisa.esbetstart.usermanagment.presentation.response.SimpleUserResponse;

/**
 * Controller for handling user-related operations.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final RegistrationUseCase registrationUseCase;
    private final CreateTransactionUseCase createTransactionUseCase;
    private final LoginUseCase loginUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetGamblerPortOut getGamblerPortOut;

    private final PresentationAuthMapper presentationAuthMapper;

    /**
     * Registers a new user.
     *
     * @param request the registration request
     */
    @PostMapping("/register")
    public void register(
            @RequestBody RegisterRequest request
    ) {
        registrationUseCase.register(request);
    }

    /**
     * Logs in a user.
     *
     * @param request the login request
     * @return the login response containing the authentication token
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        String token = loginUseCase.login(request);

        LoginResponse loginResponse = presentationAuthMapper.mapToLoginResponse(token);

        return ResponseEntity.ok(loginResponse);
    }

    /**
     * Creates a new transaction.
     *
     * @param request the create transaction request
     */
    @PostMapping("/transaction/create")
    public void transaction(
            @RequestBody CreateTransactionRequest request
    ) {
        createTransactionUseCase.createTransaction(request);
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<SimpleUserResponse> getUser(
            @PathVariable String email
    ) {

        User user = getUserUseCase.getUserByEmail(email);

        SimpleUserResponse simpleUserResponse = presentationAuthMapper.toSimpleUserResponse(user);

        return ResponseEntity.ok(simpleUserResponse);
    }

    /**
     * Updates a gambler's information.
     *
     * @param request the update gambler request
     */
    @PostMapping("/update")
    public void updateGambler(
            @RequestBody UpdateGamblerRequest request
    ) {
        updateUserUseCase.updateGambler(request);
    }

    /**
     * Retrieves the balance of a gambler by email.
     *
     * @param email the email of the gambler
     * @return the balance response
     */
    @GetMapping("/balance/{email}")
    public ResponseEntity<BalanceResponse> getBalance(
            @PathVariable String email
    ) {
        Gambler gambler = getGamblerPortOut.getGamblerByEmail(email);
        return ResponseEntity.ok(BalanceResponse.builder()
                        .withdrawableBalance(gambler.getWithdrawableBalance())
                        .balance(gambler.getBalance())
                        .bonusBalance(gambler.getBonusBalance())
                .build());
    }
}