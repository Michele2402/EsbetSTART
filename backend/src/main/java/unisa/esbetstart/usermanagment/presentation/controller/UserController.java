package unisa.esbetstart.usermanagment.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import unisa.esbetstart.usermanagment.application.port.in.RegistrationUseCase;
import unisa.esbetstart.usermanagment.presentation.request.RegisterRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final RegistrationUseCase registrationUseCase;

    @PostMapping("/register")
    public void register(
            @RequestBody RegisterRequest request
    ) {
        log.info("User Registration Started");

        registrationUseCase.register(request);

    }

}
