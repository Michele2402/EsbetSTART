package unisa.esbetstart.eventmanagement.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Rule;
import unisa.esbetstart.eventmanagement.infrastructure.entity.GameEntity;
import unisa.esbetstart.eventmanagement.infrastructure.entity.RuleEntity;
import unisa.esbetstart.eventmanagement.presentation.request.AddRuleRequest;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InfrastructureRuleMapper {

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

    public Rule toRuleModel (RuleEntity ruleEntity) {
        return Rule.builder()
                .id(ruleEntity.getId())
                .name(ruleEntity.getName())
                .position(ruleEntity.getPosition())
                .build();
    }
}
