package unisa.esbetstart.usermanagment.presentation.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleUserResponse {

    private String name;
    private String surname;
    private String username;
}
