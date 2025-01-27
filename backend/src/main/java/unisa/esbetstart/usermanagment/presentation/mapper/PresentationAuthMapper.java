package unisa.esbetstart.usermanagment.presentation.mapper;

import org.springframework.stereotype.Component;
import unisa.esbetstart.usermanagment.presentation.response.LoginResponse;

@Component
public class PresentationAuthMapper {

    public LoginResponse mapToLoginResponse(String token) {
        return LoginResponse.builder().token(token).build();
    }
}
