package com.restart.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_number")
    private Long accountNumber;
    @Column(name = "account_type")
    private String accountType;

    private Double balance;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_holder_id", referencedColumnName = "account_holder_id")
    private AccountHolder accountHolder;
}
