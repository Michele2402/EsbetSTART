package unisa.esbetstart.ticketmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;
import unisa.esbetstart.ticketmanagment.infrastructure.entity.TicketEntity;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

@Component
@RequiredArgsConstructor
public class InfrastructureTicketMapper {

    private final InfrastructureMessageMapper infrastructureMessageMapper;

    public TicketEntity toTicketEntity(Ticket ticket) {

        return TicketEntity.builder()
                .category(ticket.getCategory())
                .assignedOperator(ticket.getAssignedOperator())
                .gambler(GamblerEntity.builder()
                        .email(ticket.getOpenedBy().getEmail())
                        .build())
                .id(ticket.getId())
                .messages(ticket.getMessages()
                        .stream().map(infrastructureMessageMapper::toMessageEntity).toList())
                .build();
    }
}
