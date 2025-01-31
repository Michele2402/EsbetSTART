package unisa.esbetstart.eventmanagement.presentation.mapper;

import org.springframework.stereotype.Component;
import unisa.esbetstart.eventmanagement.domain.model.Competition;
import unisa.esbetstart.eventmanagement.presentation.response.CompetitionResponse;

import java.util.List;

@Component
public class PresentationCompetitionMapper {

    public CompetitionResponse toCompetitionResponse(Competition competition) {
        return CompetitionResponse.builder()
                .id(competition.getId().toString())
                .name(competition.getName())
                .originCountry(competition.getOriginCountry())
                .build();
    }

    public List<CompetitionResponse> toCompetitionResponseList(List<Competition> competitions) {
        return competitions
                .stream()
                .map(this::toCompetitionResponse)
                .toList();
    }
}
