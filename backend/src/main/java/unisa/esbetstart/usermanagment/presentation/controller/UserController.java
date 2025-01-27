package unisa.esbetstart.usermanagment.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.usermanagment.application.port.in.LoginUseCase;
import unisa.esbetstart.usermanagment.application.port.in.RegistrationUseCase;
import unisa.esbetstart.usermanagment.presentation.mapper.PresentationAuthMapper;
import unisa.esbetstart.usermanagment.presentation.request.LoginRequest;
import unisa.esbetstart.usermanagment.presentation.request.RegisterRequest;
import unisa.esbetstart.usermanagment.presentation.response.LoginResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final RegistrationUseCase registrationUseCase;

    private final LoginUseCase loginUseCase;

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

}
