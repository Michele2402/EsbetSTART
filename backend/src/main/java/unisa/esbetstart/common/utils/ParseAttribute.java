package unisa.esbetstart.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import unisa.esbetstart.common.exceptions.AttributeIsNullException;
import unisa.esbetstart.common.exceptions.InvalidParsingException;
import unisa.esbetstart.common.exceptions.InvalidUUIDException;
import unisa.esbetstart.ticketmanagment.domain.enums.SenderEnum;
import unisa.esbetstart.transactionmanagment.domain.enums.OfferTypeEnum;
import unisa.esbetstart.transactionmanagment.domain.enums.TransactionTypeEnum;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Slf4j
public class ParseAttribute {

    /**
     * Converts a string representation of a date into a LocalDateTime object.
     * If the string is not a valid date, logs an error message and throws an exception.
     *
     * @param date the string representation of the date to convert
     * @param infoAttribute a description of the associated attribute, used for logging and exception messages
     * @return the LocalDateTime object representing the date
     * @throws AttributeIsNullException if the string is not a valid date
     */
    public LocalDateTime convertStringIntoDate(String date, String infoAttribute) {
        try {
            return LocalDateTime.parse(date);
        } catch (Exception e) {
            log.error("{} is not a valid date", infoAttribute);
            throw new InvalidParsingException(infoAttribute + " is not a valid date");
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
     * Converts a string representation of an OfferType into an OfferTypeEnum object.
     * If the string is not a valid OfferType, logs an error message and throws an exception.
     *
     * @param type          the string representation of the OfferType to convert
     * @param infoAttribute a description of the associated attribute, used for logging and exception messages
     * @return the OfferTypeEnum object representing the OfferType
     * @throws InvalidParsingException if the string is not a valid OfferType
     */
    public OfferTypeEnum convertStringIntoOfferType(String type, String infoAttribute) {
        try {
            return OfferTypeEnum.valueOf(type);
        } catch (IllegalArgumentException e) {
            log.error("{} is not a valid OfferType", infoAttribute);
            throw new InvalidParsingException(infoAttribute + " is not a valid OfferType");
        }
    }

    /**
     * Converts a string representation of a TransactionType into a TransactionTypeEnum object.
     * If the string is not a valid TransactionType, logs an error message and throws an exception.
     *
     * @param type the string representation of the TransactionType to convert
     * @param infoAttribute a description of the associated attribute, used for logging and exception messages
     * @return the TransactionTypeEnum object representing the TransactionType
     * @throws InvalidParsingException if the string is not a valid TransactionType
     */
    public TransactionTypeEnum convertStringIntoTransactionType(String type, String infoAttribute) {
        try {
            return TransactionTypeEnum.valueOf(type);
        } catch (IllegalArgumentException e) {
            log.error("{} is not a valid TransactionType", infoAttribute);
            throw new InvalidParsingException(infoAttribute + " is not a valid TransactionType");
        }
    }

    public SenderEnum convertStringIntoSenderType(String type, String infoAttribute) {
        try {
            return SenderEnum.valueOf(type);
        } catch (IllegalArgumentException e) {
            log.error("{} is not a valid SenderType", infoAttribute);
            throw new InvalidParsingException(infoAttribute + " is not a valid SenderType");
        }
    }


}
