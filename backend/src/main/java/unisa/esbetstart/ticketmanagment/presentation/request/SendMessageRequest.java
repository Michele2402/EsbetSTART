package unisa.esbetstart.ticketmanagment.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import unisa.esbetstart.usermanagment.domain.enums.RolesEnum;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SendMessageRequest {

    private String ticketId;
    private String text;
    private LocalDateTime date;
    private RolesEnum sender;
}
