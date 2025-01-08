package unisa.esbetstart.usermanagment.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Data
@Slf4j
public class User {
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;

    @Builder
    public User(String name, String surname, String email, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        validate();
    }

    private void validate() {

        if(name == null || surname == null || email == null || username == null || password == null) {
            log.error("User attributes cannot be null");
            throw new IllegalArgumentException("User attributes cannot be null");
        }

        if(name.isEmpty() || surname.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            log.error("User attributes cannot be empty");
            throw new IllegalArgumentException("User attributes cannot be empty");
        }

        if(name.length() > 30 || surname.length() > 30 || email.length() > 30 || username.length() > 30 || password.length() > 30) {
            log.error("User attributes cannot be longer than 30 characters");
            throw new IllegalArgumentException("User attributes cannot be longer than 30 characters");
        }

        //email validation with regex
        if(!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            log.error("Email is not valid");
            throw new IllegalArgumentException("Email is not valid");
        }

        //password validation with regex
        if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")) {
            log.error("Password is not valid");
            throw new IllegalArgumentException("Password is not valid");
        }


    }
}
