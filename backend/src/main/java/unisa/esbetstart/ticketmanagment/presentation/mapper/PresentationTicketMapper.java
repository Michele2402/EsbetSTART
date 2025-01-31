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

    /**
     * Maps a list of tickets to a set of ticket responses
     * @param tickets the list of tickets
     * @return the set of ticket responses
     */
    public Set<TicketResponse> toTicketResponseSet(List<Ticket> tickets) {
        return tickets.stream().map(this::toTicketResponse).collect(Collectors.toSet());
    }

    /**
     * Maps a ticket to a ticket response
     * @param ticket the ticket
     * @return the ticket response
     */
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
