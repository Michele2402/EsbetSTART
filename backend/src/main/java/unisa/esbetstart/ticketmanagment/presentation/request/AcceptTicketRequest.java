package unisa.esbetstart.ticketmanagment.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AcceptTicketRequest {

    private String ticketId;
    private String messageDate;
    private String messageText;
    private String assignedOperator;
}
