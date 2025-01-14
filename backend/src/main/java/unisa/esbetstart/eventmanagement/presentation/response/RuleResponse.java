package unisa.esbetstart.eventmanagement.presentation.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RuleResponse {
    private String name;
    private Integer position;
}
