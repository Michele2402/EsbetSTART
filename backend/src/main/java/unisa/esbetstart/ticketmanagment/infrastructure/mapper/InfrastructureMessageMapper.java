package unisa.esbetstart.ticketmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.ticketmanagment.domain.model.Message;
import unisa.esbetstart.ticketmanagment.infrastructure.entity.MessageEntity;
import unisa.esbetstart.ticketmanagment.infrastructure.entity.TicketEntity;

@Component
@RequiredArgsConstructor
public class InfrastructureMessageMapper {

    /**
     * Maps a message model to a message entity
     * @param message the message model
     * @return the message entity
     */
    public MessageEntity toMessageEntity(Message message) {
        return MessageEntity.builder()
                .id(message.getId())
                .text(message.getText())
                .sender(message.getSender())
                .date(message.getDate())
                .read(message.isRead())
                .ticket(TicketEntity.builder()
                        .id(message.getTicket().getId()).build())
                .build();
    }

    /**
     * Maps a message entity to a message model
     * @param messageEntity the message entity
     * @return the message model
     */
    public Message toMessageModel(MessageEntity messageEntity) {
        return Message.builder()
                .id(messageEntity.getId())
                .text(messageEntity.getText())
                .date(messageEntity.getDate())
                .sender(messageEntity.getSender())
                .read(messageEntity.isRead())
                .build();
    }
}
