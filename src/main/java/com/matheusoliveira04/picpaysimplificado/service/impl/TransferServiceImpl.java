package com.matheusoliveira04.picpaysimplificado.service.impl;

import com.matheusoliveira04.picpaysimplificado.model.Transfer;
import com.matheusoliveira04.picpaysimplificado.repository.TransferRepository;
import com.matheusoliveira04.picpaysimplificado.service.AuthorizerService;
import com.matheusoliveira04.picpaysimplificado.service.TransferService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository repository;
    private final AuthorizerService authorizerService;

    public TransferServiceImpl(TransferRepository repository, AuthorizerService authorizerService) {
        this.repository = repository;
        this.authorizerService = authorizerService;
    }

    @Transactional
    @Override
    public Transfer makeTransfer(@Valid Transfer transfer) {

        //Verifica se o pagador é do tipo MERCHANT
        /*if (UserType.MERCHANT.equals(transfer.getPayer().getType())) {
            throw new RuntimeException("Type of user not allowed to make transfers");
        }*/

        // value > balance = lança exceção
        /*if (transfer.getValue().compareTo(transfer.getPayer().getWallet().getBalance()) > 0) {
            throw new RuntimeException("Insufficient balance to complete the transfer");
        }*/

        //consultar um serviço autorizador externo
        // se autorizado, debitar do pagador e creditar no recebedor
        //se não autorizado, lança exceção
        System.err.println("Consultando serviço autorizador externo...");
        System.err.println(authorizerService.authorizeTransaction());


        //Diminuir o saldo do pagador
        //Aumentar o saldo do recebedor
        //Fazer um save nos users e transfers

        //Enviar notificação para o recebedor - MOCK


        return null;
    }
}
