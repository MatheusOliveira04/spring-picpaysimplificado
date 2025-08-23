package com.matheusoliveira04.picpaysimplificado.controller;

import com.matheusoliveira04.picpaysimplificado.dto.request.TransferRequest;
import com.matheusoliveira04.picpaysimplificado.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/transfer")
@RestController
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<String> makeTransfer(@RequestBody TransferRequest transferRequest) {
        transferService.makeTransfer(transferRequest);
        return ResponseEntity.ok("Transfer successfully made!");
    }
}
