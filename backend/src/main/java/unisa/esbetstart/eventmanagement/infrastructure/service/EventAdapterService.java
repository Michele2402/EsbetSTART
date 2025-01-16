package unisa.esbetstart.eventmanagement.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.eventmanagement.application.port.out.CreateEventPortOut;
import unisa.esbetstart.eventmanagement.domain.model.Event;
import unisa.esbetstart.eventmanagement.infrastructure.mapper.InfrastructureEventMapper;
import unisa.esbetstart.eventmanagement.infrastructure.repository.EventJpaRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventAdapterService implements CreateEventPortOut {

    private final EventJpaRepository eventJpaRepository;
    private final InfrastructureEventMapper infrastructureEventMapper;

    /**
     * Adds a new event to the database.
     * @param event the event to add
     */
    @Override
    public void addEvent(Event event) {

        eventJpaRepository.save(infrastructureEventMapper.toEventEntity(event));

    }
}
