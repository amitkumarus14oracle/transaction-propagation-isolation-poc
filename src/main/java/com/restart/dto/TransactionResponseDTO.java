package com.restart.dto;

import com.restart.enums.TransactionType;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionResponseDTO {
    private double amount;
    private String description;
    private TransactionType type;
    private Date createAt;
}
