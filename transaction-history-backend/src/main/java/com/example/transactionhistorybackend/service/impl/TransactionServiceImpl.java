package com.example.transactionhistorybackend.service.impl;

import com.example.transactionhistorybackend.exceptions.ResourceNotFoundException;
import com.example.transactionhistorybackend.model.Transaction;
import com.example.transactionhistorybackend.repository.TransactionRepository;
import com.example.transactionhistorybackend.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Page<Transaction> searchTransactions(String keyword, Pageable pageable) {
        if (keyword == null || keyword.isEmpty()) {
            return transactionRepository.findAll(pageable);
        } else {
            return transactionRepository.searchByKeyword(keyword, pageable);
        }
    }

    @Override
    public Transaction updateTransaction(Long id, String description) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        transaction.setDescription(description);
        return transactionRepository.save(transaction);
    }
}