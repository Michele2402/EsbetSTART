package unisa.esbetstart.eventmanagement.application.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Rule;
import unisa.esbetstart.eventmanagement.presentation.request.AddRuleRequest;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApplicationRuleMapper {

        /**
         * Maps an AddRuleRequest to a Rule model.
         * @param request the AddRuleRequest to map
         * @return the Rule model
         */
        public Rule toRuleModel (AddRuleRequest request) {
            return Rule.builder()

                    .id(UUID.randomUUID())
                    .name(request.getName())
                    .position(request.getPosition())
                    .build();
        }

}
