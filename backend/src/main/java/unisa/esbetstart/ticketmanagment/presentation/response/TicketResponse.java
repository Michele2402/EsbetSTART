package unisa.esbetstart.ticketmanagment.presentation.response;

import lombok.*;
import unisa.esbetstart.ticketmanagment.domain.enums.TicketStatusEnum;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponse {
    private UUID id;
    private String category;
    private TicketStatusEnum status;
    private String assignedOperator;
    private String openedBy;
    private Set<MessageResponse> messages;
}
