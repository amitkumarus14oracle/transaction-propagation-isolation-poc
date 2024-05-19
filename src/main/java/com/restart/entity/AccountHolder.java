package com.restart.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AccountHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "account_holder_id")
    private String accountHolderId;
    private String name;
    private String address;
}
