package com.restart.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restart.dto.TransactionResponseDTO;
import com.restart.entity.Transaction;
import com.restart.repository.TransactionRepository;
import com.restart.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/api/transactions/account/{accountNumber}")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions(@PathVariable("accountNumber") Long accountNumber) {
        List<Transaction> transactions = transactionService.getAllTransactions(accountNumber);
        ObjectMapper mapper = new ObjectMapper();
        List<TransactionResponseDTO> transactionResponseDTOs = mapper.convertValue(transactions, new TypeReference<List<TransactionResponseDTO>>(){});
        return ResponseEntity.ok().body(transactionResponseDTOs);
    }

}
