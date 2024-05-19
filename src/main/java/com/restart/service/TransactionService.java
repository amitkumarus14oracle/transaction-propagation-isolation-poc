package com.restart.service;

import com.restart.entity.Transaction;
import com.restart.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public List<Transaction> getAllTransactions(Long accountNumber) {
        transactionRepository.findAllByAccountNumber(accountNumber);
        try {Thread.sleep(500000);} catch (Exception e) {}
        return transactionRepository.findAllByAccountNumber(accountNumber);
    }

}
