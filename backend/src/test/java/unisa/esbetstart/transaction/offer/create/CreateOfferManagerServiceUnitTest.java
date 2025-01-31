package unisa.esbetstart.transaction.offer.create;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.utils.CheckTypeAttribute;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.transactionmanagment.application.port.out.CreateOfferPortOut;
import unisa.esbetstart.transactionmanagment.application.port.out.GetOfferPortOut;
import unisa.esbetstart.transactionmanagment.application.service.CreateOfferManagerService;
import unisa.esbetstart.transactionmanagment.domain.enums.OfferTypeEnum;
import unisa.esbetstart.transactionmanagment.domain.model.Offer;
import unisa.esbetstart.transactionmanagment.presentation.request.AddOfferRequest;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class CreateOfferManagerServiceUnitTest {

    @Mock
    private CheckTypeAttribute checkTypeAttribute;

    @Mock
    private ParseAttribute parseAttribute;

    @Mock
    private GetOfferPortOut getOfferPortOut;

    @Mock
    private CreateOfferPortOut createOfferPortOut;

    @InjectMocks
    private CreateOfferManagerService createOfferManagerService;

    private AddOfferRequest validRequest;

    @BeforeEach
    void setUp() {
        validRequest = new AddOfferRequest();
        validRequest.setName("Special Offer");
        validRequest.setDescription("A limited-time offer");
        validRequest.setExpirationDate("2025-12-31T23:59:59");
        validRequest.setGoal(100);
        validRequest.setType("DEPOSIT");
        validRequest.setPrice(49.99f);
    }

    @Test
    void createOffer_Success() {
        when(parseAttribute.convertStringIntoDate(anyString(), anyString()))
                .thenReturn(LocalDateTime.of(2025, 12, 31, 23, 59, 59));
        when(parseAttribute.convertStringIntoOfferType(anyString(), anyString()))
                .thenReturn(OfferTypeEnum.DEPOSIT);

        when(getOfferPortOut.getOfferByName(anyString())).thenReturn(null);

        assertDoesNotThrow(() -> createOfferManagerService.createOffer(validRequest));

        verify(createOfferPortOut, times(1)).addOffer(any(Offer.class));
    }

    @Test
    void createOffer_ThrowsException_WhenOfferAlreadyExists() {
        when(parseAttribute.convertStringIntoDate(anyString(), anyString()))
                .thenReturn(LocalDateTime.of(2025, 12, 31, 23, 59, 59));
        when(parseAttribute.convertStringIntoOfferType(anyString(), anyString()))
                .thenReturn(OfferTypeEnum.DEPOSIT);

        when(getOfferPortOut.getOfferByName(anyString())).thenReturn(new Offer());

        ObjectIsNullException exception = assertThrows(ObjectIsNullException.class,
                () -> createOfferManagerService.createOffer(validRequest));

        assertEquals("Offer with name Special Offer already exists", exception.getMessage());

        verify(createOfferPortOut, never()).addOffer(any(Offer.class));
    }
}