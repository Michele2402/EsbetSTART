package unisa.esbetstart.ticketmanagment.presentation.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;
import unisa.esbetstart.ticketmanagment.presentation.response.TicketResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PresentationTicketMapper {

    private final PresentationMessageMapper presentationMessageMapper;

    public Set<TicketResponse> toTicketResponseSet(List<Ticket> tickets) {
        return tickets.stream().map(this::toTicketResponse).collect(Collectors.toSet());
    }

    public TicketResponse toTicketResponse(Ticket ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .category(ticket.getCategory())
                .status(ticket.getStatus())
                .assignedOperator(ticket.getAssignedOperator())
                .messages(presentationMessageMapper.toMessageResponseSet(ticket.getMessages()))
                .openedBy(ticket.getOpenedBy().getEmail())
                .build();
    }
}
