package unisa.esbetstart.usermanagment.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.common.exceptions.DomainAttributeException;

@ToString
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
        log.error("User already exists");
        throw new DomainAttributeException("User already exists");
    }
}
