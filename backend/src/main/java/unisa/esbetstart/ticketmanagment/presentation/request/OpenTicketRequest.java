package unisa.esbetstart.ticketmanagment.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OpenTicketRequest {

    private String gamblerId;
    private String category;
    private LocalDateTime messageDate;
    private String messageText;
}
