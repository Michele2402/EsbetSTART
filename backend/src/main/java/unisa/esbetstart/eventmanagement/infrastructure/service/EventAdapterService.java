package unisa.esbetstart.eventmanagement.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.eventmanagement.application.port.out.CreateEventPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetEventPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.UpdateEventPortOut;
import unisa.esbetstart.eventmanagement.domain.model.Event;
import unisa.esbetstart.eventmanagement.infrastructure.entity.EventEntity;
import unisa.esbetstart.eventmanagement.infrastructure.mapper.InfrastructureEventMapper;
import unisa.esbetstart.eventmanagement.infrastructure.repository.EventJpaRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventAdapterService implements CreateEventPortOut, UpdateEventPortOut, GetEventPortOut {

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

    /**
     * Updates an event in the database.
     * @param event the event to update
     */
    @Override
    public void updateEvent(Event event) {

            eventJpaRepository.save(infrastructureEventMapper.toEventEntityWithoutOdds(event));

    }

    @Override
    public Event getEventById(UUID eventId) {

        Optional<EventEntity> event = eventJpaRepository.findByIdWithCompetition(eventId);
        return event.map(infrastructureEventMapper::toEventModelWithoutOdds).orElse(null);

    }
}
