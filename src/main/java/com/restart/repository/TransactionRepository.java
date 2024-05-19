package com.restart.repository;

import com.restart.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    public List<Transaction> findAllByAccountNumber(Long accountNumber);
}
