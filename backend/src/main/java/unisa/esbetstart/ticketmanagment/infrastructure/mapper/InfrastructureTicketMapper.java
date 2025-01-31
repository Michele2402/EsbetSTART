package unisa.esbetstart.ticketmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;
import unisa.esbetstart.ticketmanagment.infrastructure.entity.TicketEntity;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.infrastructure.entity.GamblerEntity;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InfrastructureTicketMapper {

    private final InfrastructureMessageMapper infrastructureMessageMapper;

    /**
     * Maps a ticket model to a ticket entity
     * @param ticket the ticket model
     * @return the ticket entity
     */
    public TicketEntity toTicketEntity(Ticket ticket) {

        return TicketEntity.builder()
                .category(ticket.getCategory())
                .assignedOperator(ticket.getAssignedOperator())
                .status(ticket.getStatus())
                .gambler(GamblerEntity.builder()
                        .email(ticket.getOpenedBy().getEmail())
                        .build())
                .id(ticket.getId())
                .messages(ticket.getMessages()
                        .stream().map(infrastructureMessageMapper::toMessageEntity).collect(Collectors.toList()))
                .build();
    }

    /**
     * Maps a ticket entity to a ticket model
     * @param ticketEntity the ticket entity
     * @return the ticket model
     */
    public Ticket toTicketModel(TicketEntity ticketEntity) {

        return Ticket.builder()
                .category(ticketEntity.getCategory())
                .assignedOperator(ticketEntity.getAssignedOperator())
                .status(ticketEntity.getStatus())
                .id(ticketEntity.getId())
                .messages(ticketEntity.getMessages()
                        .stream().map(infrastructureMessageMapper::toMessageModel).collect(Collectors.toList()))
                .openedBy(Gambler.builder()
                        .email(ticketEntity.getGambler().getEmail()).build())
                .build();
    }
}
