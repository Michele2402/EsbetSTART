package unisa.esbetstart.ticketmanagment.presentation.response;

import lombok.*;
import unisa.esbetstart.ticketmanagment.domain.enums.SenderEnum;
import unisa.esbetstart.ticketmanagment.domain.model.Ticket;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponse {

    private UUID id;
    private Ticket ticket;
    private String text;
    private LocalDateTime date;
    private SenderEnum sender; // "client" or "operator"
    private boolean read;

}
