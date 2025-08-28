package com.matheusoliveira04.picpaysimplificado.service.impl;

import com.matheusoliveira04.picpaysimplificado.dto.request.TransferRequest;
import com.matheusoliveira04.picpaysimplificado.exception.service.IntegrityViolationException;
import com.matheusoliveira04.picpaysimplificado.model.Transfer;
import com.matheusoliveira04.picpaysimplificado.model.User;
import com.matheusoliveira04.picpaysimplificado.model.enums.UserType;
import com.matheusoliveira04.picpaysimplificado.repository.TransferRepository;
import com.matheusoliveira04.picpaysimplificado.service.AuthorizerService;
import com.matheusoliveira04.picpaysimplificado.service.TransferService;
import com.matheusoliveira04.picpaysimplificado.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository repository;
    private final AuthorizerService authorizerService;
    private final UserService userService;

    public TransferServiceImpl(TransferRepository repository, AuthorizerService authorizerService, UserService userService) {
        this.repository = repository;
        this.authorizerService = authorizerService;
        this.userService = userService;
    }

    @Transactional
    @Override
    public void makeTransfer(@Valid TransferRequest transferRequest) {

        User payer = userService.findById(UUID.fromString(transferRequest.getPayerId()));
        User payee = userService.findById(UUID.fromString(transferRequest.getPayeeId()));

        Transfer transfer = Transfer.builder()
                .payer(payer)
                .payee(payee)
                .value(transferRequest.getValue())
                .build();

        //Verifica se o pagador é do tipo MERCHANT
        if (UserType.MERCHANT.equals(transfer.getPayer().getType())) {
            throw new IntegrityViolationException("Type of user not allowed to make transfers");
        }

        // value > balance = lança exceção
       if (transfer.getValue().compareTo(transfer.getPayer().getWallet().getBalance()) > 0) {
            throw new IntegrityViolationException("Insufficient balance to complete the transfer");
       }

        //consultar um serviço autorizador externo
        // se autorizado, debitar do pagador e creditar no recebedor
        //se não autorizado, lança exceção
        //System.err.println("Consultando serviço autorizador externo...");
        //System.err.println(authorizerService.authorizeTransaction());


        //Diminuir o saldo do pagador
        payer.getWallet().debit(transfer.getValue());
        //Aumentar o saldo do recebedor
        payee.getWallet().credit(transfer.getValue());
        //Fazer um save nos users e transfers
        userService.update(payer);
        userService.update(payee);
        repository.save(transfer);
        //Enviar notificação para o recebedor - MOCK

    }
}
