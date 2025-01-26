package unisa.esbetstart.ticketmanagment.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.ticketmanagment.application.port.out.CreateTicketPortOut;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;
import unisa.esbetstart.ticketmanagment.infrastructure.mapper.InfrastructureTicketMapper;
import unisa.esbetstart.ticketmanagment.infrastructure.repository.TicketJpaRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketAdapterService implements CreateTicketPortOut {

    private final TicketJpaRepository ticketJpaRepository;
    private final InfrastructureTicketMapper infrastructureTicketMapper;

    @Override
    public void addTicket(Ticket ticket) {
        ticketJpaRepository.save(infrastructureTicketMapper.toTicketEntity(ticket));
    }
}
