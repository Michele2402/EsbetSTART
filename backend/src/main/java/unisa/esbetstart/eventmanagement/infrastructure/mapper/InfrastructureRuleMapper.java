package unisa.esbetstart.eventmanagement.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Rule;
import unisa.esbetstart.eventmanagement.infrastructure.entity.GameEntity;
import unisa.esbetstart.eventmanagement.infrastructure.entity.RuleEntity;

@Component
@RequiredArgsConstructor
public class InfrastructureRuleMapper {

    /**
     * Maps a rule to a Rule entity.
     * @param rule the rule to map
     * @return the Rule model
     */
    public RuleEntity toRuleEntity (Rule rule) {
        return RuleEntity.builder()
                .id(rule.getId())
                .name(rule.getName())
                .position(rule.getPosition())
                .game(GameEntity.builder()
                        .id(rule.getGame().getId())
                        .build())
                .build();
    }

    /**
     * Maps a rule to a Rule entity.
     * @param ruleEntity the rule to map
     * @return the Rule model
     */
    public Rule toRuleModel (RuleEntity ruleEntity) {
        return Rule.builder()
                .id(ruleEntity.getId())
                .name(ruleEntity.getName())
                .position(ruleEntity.getPosition())
                .build();
    }
}
