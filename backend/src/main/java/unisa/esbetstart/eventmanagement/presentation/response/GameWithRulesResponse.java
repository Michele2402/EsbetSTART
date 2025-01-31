package unisa.esbetstart.eventmanagement.presentation.response;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameWithRulesResponse {
    private String id;
    private String name;
    private Set<RuleResponse> rules;
}
