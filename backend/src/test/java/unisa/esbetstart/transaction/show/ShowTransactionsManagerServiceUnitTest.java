package unisa.esbetstart.transaction.show;

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
import unisa.esbetstart.transactionmanagment.application.port.out.GetTransactionPortOut;
import unisa.esbetstart.transactionmanagment.application.service.ShowTransactionsManagerService;
import unisa.esbetstart.transactionmanagment.domain.enums.TransactionTypeEnum;
import unisa.esbetstart.transactionmanagment.domain.model.Transaction;
import unisa.esbetstart.transactionmanagment.presentation.request.ShowUserTransactionRequest;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class ShowTransactionsManagerServiceUnitTest {

    @Mock
    private CheckTypeAttribute checkTypeAttribute;

    @Mock
    private ParseAttribute parseAttribute;

    @Mock
    private GetGamblerPortOut getGamblerPortOut;

    @Mock
    private GetTransactionPortOut getTransactionPortOut;

    @InjectMocks
    private ShowTransactionsManagerService showTransactionsManagerService;

    private ShowUserTransactionRequest validRequest;
    private Gambler gambler;

    @BeforeEach
    void setUp() {
        validRequest = new ShowUserTransactionRequest();
        validRequest.setGamblerEmail("gambler@example.com");
        validRequest.setType("DEPOSIT");

        gambler = new Gambler();
        Set<Transaction> transactions = new HashSet<>();
        transactions.add(new Transaction());
        gambler.setTransactions(transactions);
    }

    @Test
    void showTransactions_Success() {
        when(parseAttribute.convertStringIntoTransactionType(anyString(), anyString()))
                .thenReturn(TransactionTypeEnum.DEPOSIT);

        when(getGamblerPortOut.getGamblerByEmailWithTransactions(anyString(), any(TransactionTypeEnum.class)))
                .thenReturn(gambler);

        Set<Transaction> transactions = showTransactionsManagerService.showTransactions(validRequest);

        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        verify(getGamblerPortOut, times(1)).getGamblerByEmailWithTransactions(anyString(), any(TransactionTypeEnum.class));
    }

    @Test
    void showTransactions_ThrowsException_WhenGamblerNotFound() {
        lenient().when(getGamblerPortOut.getGamblerByEmailWithTransactions(
                eq("gambler@example.com"),
                eq(TransactionTypeEnum.DEPOSIT)
        )).thenReturn(null);

        ObjectIsNullException exception = assertThrows(ObjectIsNullException.class,
                () -> showTransactionsManagerService.showTransactions(validRequest));

        assertEquals("Gambler with email gambler@example.com not found", exception.getMessage());
    }

    @Test
    void showAllTransactions_Success() {
        Set<Transaction> allTransactions = new HashSet<>();
        allTransactions.add(new Transaction());

        when(getTransactionPortOut.getAllTransactions()).thenReturn(allTransactions);

        Set<Transaction> transactions = showTransactionsManagerService.showAllTransactions();

        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        verify(getTransactionPortOut, times(1)).getAllTransactions();
    }

    @Test
    void showAllTransactions_ThrowsException_WhenNoTransactionsFound() {
        when(getTransactionPortOut.getAllTransactions()).thenReturn(null);

        ObjectIsNullException exception = assertThrows(ObjectIsNullException.class,
                () -> showTransactionsManagerService.showAllTransactions());

        assertEquals("Transactions not found", exception.getMessage());
    }

}