package com.example.transactionhistorybackend.service;

import com.example.transactionhistorybackend.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    Page<Transaction> searchTransactions(String keyword, Pageable pageable);
    Transaction updateTransaction(Long id, String description);
}