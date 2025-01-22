package unisa.esbetstart.usermanagment.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.DomainAttributeException;
import unisa.esbetstart.usermanagment.domain.enums.RolesEnum;

@NoArgsConstructor
@Data
@Slf4j
@SuperBuilder
public class User {
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private RolesEnum role;

    public User(String name, String surname, String email, String username, String password, RolesEnum role) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        validate();
    }

    private void validate() {

        if(name == null || surname == null || email == null || username == null || password == null) {
            log.error("User attributes cannot be null");
            throw new DomainAttributeException("User attributes cannot be null");
        }

        if(name.isEmpty() || surname.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            log.error("User attributes cannot be empty");
            throw new DomainAttributeException("User attributes cannot be empty");
        }

        if(name.length() > 30 || surname.length() > 30 || email.length() > 30 || username.length() > 30 || password.length() > 30) {
            log.error("User attributes cannot be longer than 30 characters");
            throw new DomainAttributeException("User attributes cannot be longer than 30 characters");
        }

        //email validation with regex
        if(!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            log.error("Email is not valid");
            throw new DomainAttributeException("Email is not valid");
        }

        //password validation with regex
        if(!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")) {
            log.error("Password is not valid");
            throw new DomainAttributeException("Password is not valid");
        }


    }
}
