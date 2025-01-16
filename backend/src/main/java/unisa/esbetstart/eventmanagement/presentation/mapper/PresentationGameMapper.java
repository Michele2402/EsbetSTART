package unisa.esbetstart.eventmanagement.presentation.mapper;

import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Game;
import unisa.esbetstart.eventmanagement.presentation.response.GameWithRulesResponse;
import unisa.esbetstart.eventmanagement.presentation.response.RuleResponse;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PresentationGameMapper {

    public Set<GameWithRulesResponse> toGameWithRulesResponseSet(Set<Game> games) {
        return games.stream()
                .map(this::toGameWithRulesResponse)
                .collect(Collectors.toSet());
    }

    public GameWithRulesResponse toGameWithRulesResponse(Game game) {

        Set<RuleResponse> rules = game.getRules().stream()
                .map(rule -> RuleResponse.builder()
                        .name(rule.getName())
                        .position(rule.getPosition())
                        .build()
                )
                .collect(Collectors.toSet());

        return GameWithRulesResponse.builder()
                .name(game.getName())
                .rules(rules)
                .build();
    }
}
