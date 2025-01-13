package unisa.esbetstart.usermanagment.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.usermanagment.application.port.in.LoginUseCase;
import unisa.esbetstart.usermanagment.application.port.in.RegistrationUseCase;
import unisa.esbetstart.usermanagment.presentation.request.LoginRequest;
import unisa.esbetstart.usermanagment.presentation.request.RegisterRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final RegistrationUseCase registrationUseCase;

    private final LoginUseCase loginUseCase;

    @PostMapping("/register")
    public void register(
            @RequestBody RegisterRequest request
    ) {
        registrationUseCase.register(request);
    }

    @GetMapping("/login")
    public String login(
            @RequestBody LoginRequest request
    ) {
        return loginUseCase.login(request);
    }

}
