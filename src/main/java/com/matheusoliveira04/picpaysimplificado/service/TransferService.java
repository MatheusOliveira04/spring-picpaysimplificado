package com.matheusoliveira04.picpaysimplificado.service;

import com.matheusoliveira04.picpaysimplificado.model.Transfer;
import jakarta.validation.Valid;

public interface TransferService {

    Transfer makeTransfer(@Valid Transfer transfer);

}
