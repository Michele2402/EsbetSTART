package unisa.esbetstart.ticketmanagment.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AcceptTicketRequest {

    private String ticketId;
    private LocalDateTime messageDate;
    private String messageText;
}
