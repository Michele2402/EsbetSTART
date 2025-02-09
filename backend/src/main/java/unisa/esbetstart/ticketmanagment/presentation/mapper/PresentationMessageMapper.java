package unisa.esbetstart.ticketmanagment.presentation.mapper;

import org.springframework.stereotype.Component;
import unisa.esbetstart.ticketmanagment.domain.model.Message;
import unisa.esbetstart.ticketmanagment.presentation.response.MessageResponse;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PresentationMessageMapper {

    /**
     * Maps a list of messages to a set of message responses
     * @param messages the list of messages
     * @return the set of message responses
     */
    public Set<MessageResponse> toMessageResponseSet(List<Message> messages) {
        return messages.stream().map(this::toMessageResponse).collect(Collectors.toSet());
    }

    /**
     * Maps a message to a message response
     * @param message the message
     * @return the message response
     */
    public MessageResponse toMessageResponse(Message message) {
        return MessageResponse.builder()
                .id(message.getId())
                .text(message.getText())
                .date(message.getDate())
                .sender(message.getSender())
                .read(message.isRead())
                .build();
    }
}
