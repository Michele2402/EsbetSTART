package unisa.esbetstart.ticketmanagment.infrastructure.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.ticketmanagment.application.port.out.CreateMessagePortOut;
import unisa.esbetstart.ticketmanagment.domain.model.Message;
import unisa.esbetstart.ticketmanagment.infrastructure.mapper.InfrastructureMessageMapper;
import unisa.esbetstart.ticketmanagment.infrastructure.repository.MessageJpaRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageAdapterService implements CreateMessagePortOut {

    private final MessageJpaRepository messageJpaRepository;
    private final InfrastructureMessageMapper infrastructureMessageMapper;

    @Override
    public void updateMessage(Message message) {
        messageJpaRepository.save(infrastructureMessageMapper.toMessageEntity(message));
    }

    @Override
    public void saveMessage(Message message) {
        messageJpaRepository.save(infrastructureMessageMapper.toMessageEntity(message));
    }
}
