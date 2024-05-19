package com.restart.dto;

import com.restart.entity.AccountHolder;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class AccountRequestDTO {
    private String accountType;
    private Double balance;
    private AccountHolderDTO accountHolder;
}
