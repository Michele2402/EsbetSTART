package unisa.esbetstart.usermanagment.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import unisa.esbetstart.usermanagment.domain.enums.RolesEnum;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Slf4j
public class PrivilegedUser extends User {

    private RolesEnum role;

    @Builder
    public PrivilegedUser(String name, String surname, String email, String username, String password, RolesEnum role) {
        super(name, surname, email, username, password);
        this.role = role;
    }

}
