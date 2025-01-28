package unisa.esbetstart.usermanagment.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.usermanagment.application.port.in.GetUserUseCase;
import unisa.esbetstart.usermanagment.application.port.in.LoginUseCase;
import unisa.esbetstart.usermanagment.application.port.in.RegistrationUseCase;
import unisa.esbetstart.usermanagment.application.port.in.UpdateUserUseCase;
import unisa.esbetstart.usermanagment.domain.model.User;
import unisa.esbetstart.usermanagment.presentation.mapper.PresentationAuthMapper;
import unisa.esbetstart.usermanagment.presentation.request.LoginRequest;
import unisa.esbetstart.usermanagment.presentation.request.RegisterRequest;
import unisa.esbetstart.usermanagment.presentation.request.UpdateGamblerRequest;
import unisa.esbetstart.usermanagment.presentation.response.LoginResponse;
import unisa.esbetstart.usermanagment.presentation.response.SimpleUserResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final RegistrationUseCase registrationUseCase;
    private final LoginUseCase loginUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    private final PresentationAuthMapper presentationAuthMapper;

    @PostMapping("/register")
    public void register(
            @RequestBody RegisterRequest request
    ) {
        registrationUseCase.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        String token = loginUseCase.login(request);

        LoginResponse loginResponse = presentationAuthMapper.mapToLoginResponse(token);

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("{email}")
    public ResponseEntity<SimpleUserResponse> getUser(
            @PathVariable String email
    ) {

        User user = getUserUseCase.getUserByEmail(email);

        SimpleUserResponse simpleUserResponse = presentationAuthMapper.toSimpleUserResponse(user);

        return ResponseEntity.ok(simpleUserResponse);
    }

    @PostMapping("/update")
    public void updateGambler(
            @RequestBody UpdateGamblerRequest request
    ) {
        updateUserUseCase.updateGambler(request);
    }
}
