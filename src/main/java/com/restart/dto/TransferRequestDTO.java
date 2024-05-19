package com.restart.dto;

import lombok.Data;

@Data
public class TransferRequestDTO {
    private Long senderAccountNumber;
    private Long receiverAccountNumber;
    private Double amount;
    private String description;
}
