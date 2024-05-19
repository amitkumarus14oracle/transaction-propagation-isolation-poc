package com.restart.service;

import com.restart.entity.Account;
import com.restart.entity.Transaction;
import com.restart.enums.TransactionType;
import com.restart.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionService transactionService;
    public Double getBalance(Long accountNumber) {
        Optional<Account> account = accountRepository.findById(accountNumber);
        return account.isPresent() ? account.get().getBalance() : 0.0;
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public boolean transfer(Long senderAccountNumber, Long receiverAccountNumber, Double amount, String description) {
        try {
            Optional<Account> senderAccount = accountRepository.findById(senderAccountNumber);
            Optional<Account> receiverAccount = accountRepository.findById(receiverAccountNumber);
            senderAccount.ifPresent(account -> {
                account.setBalance(account.getBalance()-amount);
                transactionService.addTransaction(Transaction.builder().accountNumber(account.getAccountNumber()).type(TransactionType.DEBIT).amount(amount).createAt(new Date()).description(description).build());
            });
            receiverAccount.ifPresent(account -> {
                account.setBalance(account.getBalance() + amount);
                transactionService.addTransaction(Transaction.builder().accountNumber(account.getAccountNumber()).type(TransactionType.CREDIT).amount(amount).createAt(new Date()).description(description).build());
            });
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
