package unisa.esbetstart.usermanagment.presentation.mapper;

import org.springframework.stereotype.Component;
import unisa.esbetstart.usermanagment.domain.model.User;
import unisa.esbetstart.usermanagment.presentation.response.LoginResponse;
import unisa.esbetstart.usermanagment.presentation.response.SimpleUserResponse;

@Component
public class PresentationAuthMapper {

    public LoginResponse mapToLoginResponse(String token) {
        return LoginResponse.builder().token(token).build();
    }

    public SimpleUserResponse toSimpleUserResponse(User user) {
        return SimpleUserResponse.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .build();
    }
}
