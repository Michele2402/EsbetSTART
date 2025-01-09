package unisa.esbetstart.usermanagment.application.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import unisa.esbetstart.common.exceptions.AttributeIsNullException;
import unisa.esbetstart.common.exceptions.InvalidUUIDException;

import java.util.UUID;

@Component
@Slf4j
public class CheckTypeAttribute {

    /**
     * Checks if a string is null or empty. If so, logs an error message and throws an exception.
     *
     * @param string        the string to check
     * @param infoAttribute a description of the associated attribute, used for logging and exception messages
     * @throws AttributeIsNullException if the string is null or empty
     */
    public void checkStringIsNullOrEmpty(String string, String infoAttribute) {
        if (string == null || string.isEmpty()) {
            log.error("{} is null or empty", infoAttribute);
            throw new AttributeIsNullException(infoAttribute + " is null or empty");
        }
    }

    /**
     * Checks if a UUID is null or invalid. If so, logs an error message and throws an exception.
     *
     * @param uuid          the UUID to check
     * @param infoAttribute a description of the associated attribute, used for logging and exception messages
     * @throws InvalidUUIDException if the UUID is null or invalid
     */
    public UUID checkUUIDIsNullOrInvalid(String uuid, String infoAttribute) {
        if (uuid == null || uuid.isEmpty()) {
            log.error("{} is null or empty", infoAttribute);
            throw new AttributeIsNullException(infoAttribute + " is null or empty");
        }
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            log.error("{} is not a valid UUID", infoAttribute);
            throw new InvalidUUIDException(infoAttribute + " is not a valid UUID");
        }
    }

    /**
     * Checks if an integer is null or negative. If so, logs an error message and throws an exception.
     *
     * @param integer       the integer to check
     * @param infoAttribute a description of the associated attribute, used for logging and exception messages
     * @throws AttributeIsNullException if the integer is null or negative
     */
    public void checkIntegerIsNullOrNegative(Integer integer, String infoAttribute) {
        if (integer == null || integer < 0) {
            log.error("{} is null or negative", infoAttribute);
            throw new AttributeIsNullException(infoAttribute + " is null or negative");
        }

    }

}