package com.example.transactionhistorybackend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "TRX_AMOUNT")
    private Double trxAmount;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TRX_DATE")
    private String trxDate;

    @Column(name = "TRX_TIME")
    private String trxTime;

    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Version
    private Long version;
}