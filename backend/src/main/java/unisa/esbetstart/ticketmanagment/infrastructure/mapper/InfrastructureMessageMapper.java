package unisa.esbetstart.ticketmanagment.infrastructure.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import unisa.esbetstart.ticketmanagment.domain.model.Message;
import unisa.esbetstart.ticketmanagment.infrastructure.entity.MessageEntity;
import unisa.esbetstart.ticketmanagment.infrastructure.entity.TicketEntity;

@Component
@RequiredArgsConstructor
public class InfrastructureMessageMapper {
    public MessageEntity toMessageEntity(Message message) {
        return MessageEntity.builder()
                .id(message.getId())
                .text(message.getText())
                .sender(message.getSender())
                .read(message.isRead())
                .ticket(TicketEntity.builder()
                        .id(message.getTicket().getId()).build())
                .build();
    }

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
