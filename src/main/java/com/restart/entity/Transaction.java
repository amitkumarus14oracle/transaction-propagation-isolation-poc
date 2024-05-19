package com.restart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restart.converter.TransactionTypeConverter;
import com.restart.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transaction_id")
    @JsonIgnore
    private String transactionId;
    @Column(name = "account_number")
    @JsonIgnore
    private Long accountNumber;
    private double amount;
    private String description;
    @Column(name = "transaction_type")
    @Convert(converter = TransactionTypeConverter.class)
    private TransactionType type;
    @Column(name = "created_at")
    private Date createAt;
}
