package unisa.esbetstart.ticketmanagment.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.ticketmanagment.application.port.out.CreateTicketPortOut;
import unisa.esbetstart.ticketmanagment.application.port.out.GetTicketPortOut;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;
import unisa.esbetstart.ticketmanagment.infrastructure.entity.TicketEntity;
import unisa.esbetstart.ticketmanagment.infrastructure.mapper.InfrastructureTicketMapper;
import unisa.esbetstart.ticketmanagment.infrastructure.repository.TicketJpaRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketAdapterService implements CreateTicketPortOut, GetTicketPortOut {

    private final TicketJpaRepository ticketJpaRepository;
    private final InfrastructureTicketMapper infrastructureTicketMapper;

    @Override
    public void addTicket(Ticket ticket) {
        ticketJpaRepository.save(infrastructureTicketMapper.toTicketEntity(ticket));
    }

    @Override
    public Ticket getTicketById(UUID id) {
        Optional<TicketEntity> optionalTicketEntity = ticketJpaRepository.findById(id);

        return optionalTicketEntity
                .map(infrastructureTicketMapper::toTicketModel).orElse(null);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        ticketJpaRepository.save(infrastructureTicketMapper.toTicketEntity(ticket));
    }
}
