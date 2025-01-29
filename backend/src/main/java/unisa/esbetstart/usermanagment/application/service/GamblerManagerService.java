package unisa.esbetstart.usermanagment.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unisa.esbetstart.common.exceptions.IndexOutOfBoundException;
import unisa.esbetstart.common.exceptions.ObjectIsNullException;
import unisa.esbetstart.common.exceptions.ObjectNotFoundException;
import unisa.esbetstart.common.utils.ParseAttribute;
import unisa.esbetstart.transactionmanagment.application.port.out.DeleteActivatedOfferPortOut;
import unisa.esbetstart.transactionmanagment.application.port.out.SaveTransactionPortOut;
import unisa.esbetstart.transactionmanagment.domain.enums.TransactionTypeEnum;
import unisa.esbetstart.transactionmanagment.domain.model.ActivatedOffer;
import unisa.esbetstart.transactionmanagment.domain.model.Transaction;
import unisa.esbetstart.usermanagment.application.port.in.CreateTransactionUseCase;
import unisa.esbetstart.usermanagment.application.port.out.GetGamblerPortOut;
import unisa.esbetstart.usermanagment.application.port.out.UpdateUserPortOut;
import unisa.esbetstart.usermanagment.domain.model.Gambler;
import unisa.esbetstart.usermanagment.presentation.request.CreateTransactionRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class GamblerManagerService implements CreateTransactionUseCase {


    private final ParseAttribute parseAttribute;
    private final GetGamblerPortOut getGamblerPortOut;
    private final UpdateUserPortOut updateGamblerPortOut;
    private final DeleteActivatedOfferPortOut deleteActivatedOfferPortOut;
    private final SaveTransactionPortOut saveTransactionPortOut;

    @Override
    public void createTransaction(CreateTransactionRequest request) {

        log.info("Creating transaction with type: {} for gambler with id: {} and value: {}", request.getTransactionType(), request.getGamblerEmail(), request.getTransactionValue());

        //check if the gambler exists
        Gambler gambler = getGamblerPortOut.getGamblerByEmailWithOffers(request.getGamblerEmail());

        //null check
        if (gambler == null) {
            log.error("Gambler with email: {} not found", request.getGamblerEmail());
            throw new ObjectNotFoundException("Gambler with email: " + request.getGamblerEmail() + " not found");
        }

        List<UUID> oldActivatedOffers = gambler.getActivatedOffers().stream()
                .map(ActivatedOffer::getId)
                .toList();

        //check if the request is valid
        checkTransactionRequest(request);

        if (request.getTransactionType().equals(TransactionTypeEnum.DEPOSIT)) {
            gambler.deposit(request.getTransactionValue());
        } else if (request.getTransactionType().equals(TransactionTypeEnum.WITHDRAWAL)) {
            gambler.withdraw(request.getTransactionValue());
        }

        //delete all the activated offers that are completed
        for (UUID activatedOfferId : oldActivatedOffers) {
            if(!gambler.getActivatedOffers().stream().map(ActivatedOffer::getId).toList().contains(activatedOfferId)) {
                deleteActivatedOfferPortOut.deleteActivatedOfferById(activatedOfferId);
            }
        }

        //update the gambler
        updateGamblerPortOut.updateGambler(gambler);

        Transaction toSave = Transaction.builder()
                .amount(request.getTransactionValue())
                .type(request.getTransactionType())
                .date(LocalDateTime.now())
                .gambler(gambler)
                .build();

        saveTransactionPortOut.saveTransaction(toSave);

        log.info("Transaction created successfully");

    }


    private void checkTransactionRequest(CreateTransactionRequest request) {
        //check if the request is valid
        if (request == null) {
            log.error("Request is null");
            throw new ObjectIsNullException("Request is null");
        }

        //check if the transaction type is valid
        if (request.getTransactionType() == null) {
            log.error("Transaction type is null");
            throw new ObjectIsNullException("Transaction type is null");
        }

        //check if the transaction value is valid
        if (request.getTransactionValue() == null || request.getTransactionValue() <= 0) {
            log.error("Invalid transaction value: {}", request.getTransactionValue());
            throw new IndexOutOfBoundException("Invalid transaction value: " + request.getTransactionValue());
        }
    }

}
