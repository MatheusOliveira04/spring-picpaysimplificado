package com.matheusoliveira04.picpaysimplificado.service;

import com.matheusoliveira04.picpaysimplificado.dto.request.TransferRequest;
import com.matheusoliveira04.picpaysimplificado.model.Transfer;
import jakarta.validation.Valid;

public interface TransferService {

    void makeTransfer(@Valid TransferRequest transferRequest);

}
