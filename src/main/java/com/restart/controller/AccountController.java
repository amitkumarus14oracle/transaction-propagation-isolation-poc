package com.restart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restart.dto.AccountRequestDTO;
import com.restart.dto.TransferRequestDTO;
import com.restart.entity.Account;
import com.restart.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/api/balance/account/{accountNumber}")
    public ResponseEntity<Double> getBalance(@PathVariable("accountNumber") Long accountNumber) {
        Double balance = accountService.getBalance(accountNumber);
        return ResponseEntity.ok().body(balance);
    }
    @PatchMapping("/api/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody TransferRequestDTO transferRequestDTO) {
        boolean hasTransferred = accountService.transfer(transferRequestDTO.getSenderAccountNumber(), transferRequestDTO.getReceiverAccountNumber(), transferRequestDTO.getAmount(), transferRequestDTO.getDescription());
        return hasTransferred ? ResponseEntity.ok().body("Transferred Successfully") : ResponseEntity.ok().body("Transfer Failed");
    }

    @PostMapping("/create/account")
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        ObjectMapper mapper = new ObjectMapper();
        Account account = accountService.createAccount(mapper.convertValue(accountRequestDTO, Account.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }
}
