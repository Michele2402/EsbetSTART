package unisa.esbetstart.eventmanagement.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.eventmanagement.application.port.out.CreateEventPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.GetEventPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.RemoveEventPortOut;
import unisa.esbetstart.eventmanagement.application.port.out.UpdateEventPortOut;
import unisa.esbetstart.eventmanagement.domain.model.Event;
import unisa.esbetstart.eventmanagement.infrastructure.entity.EventEntity;
import unisa.esbetstart.eventmanagement.infrastructure.mapper.InfrastructureEventMapper;
import unisa.esbetstart.eventmanagement.infrastructure.repository.EventJpaRepository;
import unisa.esbetstart.slipmanagment.infrastructure.repository.OddStaticJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventAdapterService implements
        CreateEventPortOut,
        UpdateEventPortOut,
        GetEventPortOut,
        RemoveEventPortOut {

    private final EventJpaRepository eventJpaRepository;
    private final InfrastructureEventMapper infrastructureEventMapper;
    private final OddStaticJpaRepository oddStaticJpaRepository;

    /**
     * Adds a new event to the database.
     *
     * @param event the event to add
     */
    @Override
    public void addEvent(Event event) {

        eventJpaRepository.save(infrastructureEventMapper.toEventEntity(event));

    }

    /**
     * Updates an event in the database.
     *
     * @param event the event to update
     */
    @Override
    public void updateEvent(Event event) {

        eventJpaRepository.save(infrastructureEventMapper.toEventEntityWithoutOdds(event));

    }

    /**
     * Gets an event by its ID, it has the competition in it.
     *
     * @param eventId the ID of the event
     * @return the event
     */
    @Override
    public Event getEventByIdWithoutOdds(UUID eventId) {

        Optional<EventEntity> event = eventJpaRepository.findByIdWithCompetition(eventId);
        return event.map(infrastructureEventMapper::toEventModelWithoutOdds).orElse(null);

    }

    /**
     * Gets a list of events by their competition ID
     *
     * @param competitionId the ID of the competition
     * @return the list of events
     */
    @Override
    public List<Event> getAllByCompetitionId(UUID competitionId) {

        List<EventEntity> events = eventJpaRepository.findAllByCompetitionIdWithOdds(competitionId);
        return infrastructureEventMapper.toModelWithOddsList(events);
    }

    /**
     * Gets an event by its ID. Has the odds in it.
     *
     * @param eventId the ID of the event
     * @return the event
     */
    @Override
    public Event getEventByIdToBetPlaced(UUID eventId) {
        Optional<EventEntity> event = eventJpaRepository.findByIdToBetPlaced(eventId);
        return event.map(infrastructureEventMapper::toEventModelWithAlotOfDetails).orElse(null);
    }

    /**
     * Removes an event from the database.
     *
     * @param eventId the ID of the event to remove
     */
    @Override
    public void removeEvent(UUID eventId) {
        eventJpaRepository.deleteById(eventId);

    }

    @Override
    public void endEvent(Event event) {

        EventEntity eventEntity = infrastructureEventMapper.toEventEntityToBetPlaced(event);
        eventJpaRepository.save(eventEntity);

        eventEntity.getOdds()
                .forEach(oddEntity -> oddStaticJpaRepository.saveAll(oddEntity.getOddStatics()
                        .stream().peek(oddStatic -> oddStatic.setOdd(null)).collect(Collectors.toSet())));

        eventEntity.getOdds().forEach(odd -> oddStaticJpaRepository.saveAll(odd.getOddStatics()));

        eventJpaRepository.deleteById(event.getId());

    }
}
