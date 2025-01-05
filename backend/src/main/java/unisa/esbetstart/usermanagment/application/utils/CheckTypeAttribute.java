package unisa.esbetstart.usermanagment.application.utils;

import org.springframework.stereotype.Component;

@Component
public class CheckTypeAttribute {

    private void checkStringIsNullOrEmpty(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("The attribute cannot be null or empty");
        }
    }
}
