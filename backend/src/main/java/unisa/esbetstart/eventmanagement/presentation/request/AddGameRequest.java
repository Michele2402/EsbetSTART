package unisa.esbetstart.eventmanagement.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddGameRequest {

    private String name;
    private List<String> rules;
}
